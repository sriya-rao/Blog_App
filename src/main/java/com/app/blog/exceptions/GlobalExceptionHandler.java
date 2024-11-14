package com.app.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)	
	public ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundException ex){
		String msgString=ex.getMessage();
		return new ResponseEntity<String>(msgString, HttpStatus.NOT_FOUND);
	}
}
