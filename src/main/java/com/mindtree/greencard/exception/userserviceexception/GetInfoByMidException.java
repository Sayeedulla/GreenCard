package com.mindtree.greencard.exception.userserviceexception;

import com.mindtree.greencard.exception.GreenCardException;

@SuppressWarnings("serial")
public class GetInfoByMidException extends GreenCardException {

	public GetInfoByMidException() {
		super();
	}

	public GetInfoByMidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GetInfoByMidException(String message, Throwable cause) {
		super(message, cause);
	}

	public GetInfoByMidException(String message) {
		super(message);
	}

	public GetInfoByMidException(Throwable cause) {
		super(cause);
	}
}