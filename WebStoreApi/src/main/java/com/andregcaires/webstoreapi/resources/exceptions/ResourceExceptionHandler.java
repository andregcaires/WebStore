package com.andregcaires.webstoreapi.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.andregcaires.webstoreapi.services.exceptions.ConstraintException;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

/*
 * Classe auxiliar para interceptar exceções dos recursos REST
 * */

@ControllerAdvice
public class ResourceExceptionHandler {

	// Assinatura obrigatória
	@ExceptionHandler(ObjectNotFoundException.class) // exception jogada no SERVICE 
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<StandardError> constraintException(ConstraintException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
