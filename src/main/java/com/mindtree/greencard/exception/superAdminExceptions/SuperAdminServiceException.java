package com.mindtree.greencard.exception.superAdminExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SuperAdminServiceException extends RuntimeException{

	public SuperAdminServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperAdminServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public SuperAdminServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SuperAdminServiceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SuperAdminServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
}
