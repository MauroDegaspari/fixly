package com.helpdesk.fixly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class NotFoundExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandarError> notFoundException(NotFoundException ex, HttpServletRequest request ){
		
		StandarError erro = new StandarError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"Objeto não encondrado.",ex.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
