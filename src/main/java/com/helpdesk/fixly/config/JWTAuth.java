package com.helpdesk.fixly.config;

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
import com.helpdesk.fixly.security.JWTUtil;
import com.helpdesk.fixly.security.UserSS;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuth extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager auth;		//principal interface de autenticação
	private JWTUtil jwt;
	
	
	public JWTAuth(AuthenticationManager auth, JWTUtil jwt) {
		super();
		this.auth = auth;
		this.jwt = jwt;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
			
		try {
			UserCredenciaisDto creadenciais = new ObjectMapper().readValue( request.getInputStream(), UserCredenciaisDto.class); //recupera o corpo da requisição com dados binario
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creadenciais.getEmail(), creadenciais.getSenha(), new ArrayList<>());
			Authentication authentication = auth.authenticate(authToken);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			
			String username = ((UserSS) authResult.getPrincipal()).getUsername();
			String token  = jwt.geradorToken(username);	
			
			response.setHeader("access-control-expose-headers", "Authorization");
			response.setHeader("Authorization", "Bearer" + token);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
			
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	private CharSequence json() {
		long date = new Date().getTime();
		return "{"											+
		       "\"timestamp\": " + date + ","				+
		       "\"status\": 401,"							+
		       "\"error\": \"Não autorizado\","				+
		       "\"message\": \"Email ou senha inválido\","	+
		       "\"path\": \"/login\""						+
		       "}";
	}
	
	
	
}
