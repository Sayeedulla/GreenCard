package com.mindtree.greencard.exception.userserviceexception;

import com.mindtree.greencard.exception.GreenCardException;

@SuppressWarnings("serial")
public class SaveNewGreenCardException extends GreenCardException {

	public SaveNewGreenCardException() {
		super();
	}

	public SaveNewGreenCardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SaveNewGreenCardException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaveNewGreenCardException(String message) {
		super(message);
	}

	public SaveNewGreenCardException(Throwable cause) {
		super(cause);
	}
}