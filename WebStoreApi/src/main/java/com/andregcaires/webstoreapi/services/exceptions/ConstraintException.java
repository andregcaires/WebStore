package com.andregcaires.webstoreapi.services.exceptions;

public class ConstraintException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstraintException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConstraintException(String message) {
		super(message);
	}
	
	

}
