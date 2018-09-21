package com.mindtree.greencard.exception.userserviceexception;

import com.mindtree.greencard.exception.GreenCardException;

@SuppressWarnings("serial")
public class SaveGreenCardByGuestException extends GreenCardException{

	public SaveGreenCardByGuestException() {
		super();
	}

	public SaveGreenCardByGuestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SaveGreenCardByGuestException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaveGreenCardByGuestException(String message) {
		super(message);
	}

	public SaveGreenCardByGuestException(Throwable cause) {
		super(cause);
	}
}