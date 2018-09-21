package com.mindtree.greencard.exception.userserviceexception;

import com.mindtree.greencard.exception.GreenCardException;

@SuppressWarnings("serial")
public class GreenCardLifeCycleException extends GreenCardException {

	public GreenCardLifeCycleException() {
		super();
	}

	public GreenCardLifeCycleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GreenCardLifeCycleException(String message, Throwable cause) {
		super(message, cause);
	}

	public GreenCardLifeCycleException(String message) {
		super(message);
	}

	public GreenCardLifeCycleException(Throwable cause) {
		super(cause);
	}
}