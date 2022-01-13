package com.coding.app.exceptions;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		super("La liste est vide");
	}
}
