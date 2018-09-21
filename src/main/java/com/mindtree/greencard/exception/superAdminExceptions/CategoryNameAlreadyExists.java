package com.mindtree.greencard.exception.superAdminExceptions;

@SuppressWarnings("serial")
public class CategoryNameAlreadyExists extends Exception {

	public CategoryNameAlreadyExists() {
		super();
	}

	public CategoryNameAlreadyExists(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CategoryNameAlreadyExists(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CategoryNameAlreadyExists(String arg0) {
		super(arg0);
	}

	public CategoryNameAlreadyExists(Throwable arg0) {
		super(arg0);
	}

}