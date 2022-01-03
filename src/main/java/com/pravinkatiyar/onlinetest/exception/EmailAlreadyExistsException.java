package com.pravinkatiyar.onlinetest.exception;

public class EmailAlreadyExistsException extends RuntimeException {

	private String msg;

	public EmailAlreadyExistsException(String msg) {
		super(msg);
	
	}
	
}
