package com.helpdesk.fixly.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.helpdesk.fixly.services.UserSService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthFilter extends BasicAuthenticationFilter {
	
	private JWTUtil util;
	private UserSService service;

	public JWTAuthFilter(AuthenticationManager authenticationManager, JWTUtil util, UserSService service) {
		super(authenticationManager);
		
		this.util = util;
		this.service = service;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
			if(authToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(util.tokenValido(token)) {
			String userName = util.getUserName(token);
			UserDetails details = service.loadUserByUsername(userName);
			
			return new UsernamePasswordAuthenticationToken(details.getUsername(),null, details.getAuthorities());
		}
		return null;
	}

}
