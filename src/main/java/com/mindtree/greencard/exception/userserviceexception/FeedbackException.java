package com.mindtree.greencard.exception.userserviceexception;

import com.mindtree.greencard.exception.GreenCardException;

@SuppressWarnings("serial")
public class FeedbackException extends GreenCardException {

	public FeedbackException() {
		super();
	}

	public FeedbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FeedbackException(String message, Throwable cause) {
		super(message, cause);
	}

	public FeedbackException(String message) {
		super(message);
	}

	public FeedbackException(Throwable cause) {
		super(cause);
	}
}