package com.mindtree.greencard.exception.superadminexceptions;

@SuppressWarnings("serial")
public class InvalidTypeException extends RuntimeException {

	public InvalidTypeException() {
		super();
	}

	public InvalidTypeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidTypeException(String arg0) {
		super(arg0);
	}

	public InvalidTypeException(Throwable arg0) {
		super(arg0);
	}
}