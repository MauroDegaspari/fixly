package com.helpdesk.fixly.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.helpdesk.fixly.security.JWTAuthenticationFilter;
import com.helpdesk.fixly.security.JWTAuthorizationFilter;
import com.helpdesk.fixly.security.JWTUtil;
import com.helpdesk.fixly.services.UserSService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final UserSService userSService;

    public SecurityConfig(JWTUtil jwtUtil, UserSService userSService) {
        this.jwtUtil = jwtUtil;
        this.userSService = userSService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        
        AuthenticationManager authManager = authConfig.getAuthenticationManager();

        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Registra os Filtros na Ordem correta
            .addFilter(new JWTAuthenticationFilter(authManager, jwtUtil))
            .addFilter(new JWTAuthorizationFilter(authManager, jwtUtil, userSService))
            
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
            )
            
            // Garante que a API não salvará estado de sessão em memória (Stateless)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // Captura erros globais de falta de Token/Acesso Proibido nas rotas normais em JSON
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json;charset=UTF-8");
                    long date = System.currentTimeMillis();
                    String json = "{"
                            + "\"timestamp\": " + date + ","
                            + "\"status\": 403,"
                            + "\"error\": \"Forbidden\","
                            + "\"message\": \"Acesso negado. Token ausente ou inválido.\","
                            + "\"path\": \"" + request.getRequestURI() + "\""
                            + "}";
                    response.getWriter().append(json);
                })
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // Bean para Criptografia de Senhas - Utilizado no seu fluxo de cadastro e no login automático do Spring
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}