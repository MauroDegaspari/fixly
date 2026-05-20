package com.helpdesk.fixly.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.fixly.dtos.UserCredenciaisDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authManager, JWTUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        // Define explicitamente qual é a URL de login
        setFilterProcessesUrl("/login"); 
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserCredenciaisDto credenciais = new ObjectMapper().readValue(request.getInputStream(), UserCredenciaisDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    credenciais.getEmail(), credenciais.getSenha(), new ArrayList<>());
            
            return authManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException("Falha ao ler dados da requisição", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.geradorToken(username);

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        
        long date = new Date().getTime();
        String json = "{"
                + "\"timestamp\": " + date + ","
                + "\"status\": 401,"
                + "\"error\": \"Não autorizado\","
                + "\"message\": \"E-mail ou senha inválidos\","
                + "\"path\": \"/login\""
                + "}";
                
        response.getWriter().append(json);
    }
}