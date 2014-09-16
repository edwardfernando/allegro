package com.allegro.api.exception;

public enum ErrorCode {

	AUTHENTICATION_ERROR(9999, "Authentication Error"), 
	SYSTEM_ERROR(0000, "System Error"), 
	RESOURCE_EXIST(1000,"Resoure Already Exist");

	private final int value;
	private final String reasonPhrase;

	private ErrorCode(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
}
