package com.coding.app.exceptions;


import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddUnsatisfiedException extends Exception {
	private HashMap<String, String> errors = new HashMap<String,String>();

	private static final long serialVersionUID = 1L;

	public AddUnsatisfiedException(String msg) {
		super(msg);
	}

	public AddUnsatisfiedException() {

	}

	@Override
	public String toString() {
		System.out.println(errors.toString());
		return errors.toString();
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}
	
	
}