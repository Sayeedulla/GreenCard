package com.mindtree.greencard.exception.superAdminExceptions;

@SuppressWarnings("serial")
public class InvalidEmailFormatException extends RuntimeException {

	public InvalidEmailFormatException() {
		super();
	}

	public InvalidEmailFormatException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidEmailFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidEmailFormatException(String arg0) {
		super(arg0);
	}

	public InvalidEmailFormatException(Throwable arg0) {
		super(arg0);
	}
}