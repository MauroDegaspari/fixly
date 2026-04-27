package com.helpdesk.fixly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandler {
	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandarError> notFoundException(NotFoundException ex, HttpServletRequest request ){
		
		StandarError erro = new StandarError(System.currentTimeMillis(),
											 HttpStatus.NOT_FOUND.value(),
											 "Objeto não encondrado.",
											 ex.getMessage(),
											 request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandarError> dataIntegrityViolationException(NotFoundException ex, HttpServletRequest request ){
		
		StandarError erro = new StandarError(System.currentTimeMillis(),
											 HttpStatus.BAD_REQUEST.value(),
											 "Violação de dados.",
											 ex.getMessage(),
											 request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
