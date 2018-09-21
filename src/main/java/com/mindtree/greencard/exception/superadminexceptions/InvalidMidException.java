package com.mindtree.greencard.exception.superadminexceptions;

@SuppressWarnings("serial")
public class InvalidMidException extends Exception {

	public InvalidMidException() {
		super();
	}

	public InvalidMidException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidMidException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidMidException(String arg0) {
		super(arg0);
	}

	public InvalidMidException(Throwable arg0) {
		super(arg0);
	}
}