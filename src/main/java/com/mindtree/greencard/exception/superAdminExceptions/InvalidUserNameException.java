package com.mindtree.greencard.exception.superAdminExceptions;

@SuppressWarnings("serial")
public class InvalidUserNameException extends RuntimeException {

	public InvalidUserNameException() {
		super();
	}

	public InvalidUserNameException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidUserNameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidUserNameException(String arg0) {
		super(arg0);
	}

	public InvalidUserNameException(Throwable arg0) {
		super(arg0);
	}
}