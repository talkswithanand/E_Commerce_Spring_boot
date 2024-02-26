package com.tyss.shopapp.exception;

public class NotAuthorisedException extends RuntimeException {
	
	private String message = "Not have Authority to do operation";

	public NotAuthorisedException() {
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public NotAuthorisedException(String message) {
		this.message = message;
	}
	
}
