package com.mindtree.greencard.exception.AdminExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdminException extends RuntimeException{

	public AdminException() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public AdminException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	

}
