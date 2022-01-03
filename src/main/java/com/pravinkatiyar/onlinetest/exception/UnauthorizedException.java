package com.pravinkatiyar.onlinetest.exception;

public class UnauthorizedException extends RuntimeException {

	private String msg;

	public UnauthorizedException(String msg) {
		super(msg);
	}
	
}
