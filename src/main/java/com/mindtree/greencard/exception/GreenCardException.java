package com.mindtree.greencard.exception;

@SuppressWarnings("serial")
public class GreenCardException extends Exception {

	public GreenCardException() {
		super();
	}

	public GreenCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GreenCardException(String message, Throwable cause) {
		super(message, cause);
	}

	public GreenCardException(String message) {
		super(message);
	}

	public GreenCardException(Throwable cause) {
		super(cause);
	}
}