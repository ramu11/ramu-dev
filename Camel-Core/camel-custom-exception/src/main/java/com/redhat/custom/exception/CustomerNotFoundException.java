package com.redhat.custom.exception;

public class CustomerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7721472492496101404L;

	public CustomerNotFoundException() {

		super();

	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
