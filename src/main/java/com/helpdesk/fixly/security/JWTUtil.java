package com.helpdesk.fixly.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private long expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@SuppressWarnings("deprecation")
	public String geradorToken(String email) {
		
		
		return Jwts.builder()
				.setSubject(email)     												 //Define o valor! informações do token ;
				.setExpiration(new Date(System.currentTimeMillis() + expiration))   //Setta o valor de data atual + o pre definido no properties de expiração
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())               // Algoritmo usado para assinatura do token, 2 parametro é a chave usada no properties
				.compact(); 
	}			

}
