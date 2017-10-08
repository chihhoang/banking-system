package com.bank.exceptions;

public class InvalidAccountTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAccountTypeException() {
		super("InvalidAccountTypeException");
	}
}
