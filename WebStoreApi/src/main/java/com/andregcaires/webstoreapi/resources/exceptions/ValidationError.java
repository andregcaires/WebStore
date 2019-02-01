package com.andregcaires.webstoreapi.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();;
	
	public ValidationError(Integer status, String message, Long timestamp) {
		super(status, message, timestamp);

	}

	// nome do método mudado para getErrors para aparecer no json como "errors"
	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String message) {
		this.list.add(new FieldMessage(fieldName, message));
	}
	
	
	
}
