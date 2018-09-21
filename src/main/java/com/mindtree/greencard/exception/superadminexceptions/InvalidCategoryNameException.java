package com.mindtree.greencard.exception.superadminexceptions;

@SuppressWarnings("serial")
public class InvalidCategoryNameException extends Exception {

	public InvalidCategoryNameException() {
		super();
	}

	public InvalidCategoryNameException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidCategoryNameException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidCategoryNameException(String arg0) {
		super(arg0);
	}

	public InvalidCategoryNameException(Throwable arg0) {
		super(arg0);
	}
}