package com.mindtree.greencard.exception.userserviceexception;

import com.mindtree.greencard.exception.GreenCardException;

@SuppressWarnings("serial")
public class UserByMidPassNotExistsException extends GreenCardException {

	public UserByMidPassNotExistsException() {
		super();
	}

	public UserByMidPassNotExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserByMidPassNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserByMidPassNotExistsException(String message) {
		super(message);
	}

	public UserByMidPassNotExistsException(Throwable cause) {
		super(cause);
	}
}