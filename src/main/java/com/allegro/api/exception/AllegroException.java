package com.allegro.api.exception;

import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class AllegroException extends RuntimeException {
	private Errors errors;
	private ErrorCode errorCode = ErrorCode.SYSTEM_ERROR;

	public AllegroException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

	public AllegroException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public AllegroException(String message) {
		super(message);
	}

	public Errors getErrors() {
		return errors;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
