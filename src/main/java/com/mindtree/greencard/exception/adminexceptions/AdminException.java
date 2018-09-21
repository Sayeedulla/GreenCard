package com.mindtree.greencard.exception.adminexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdminException extends RuntimeException {

	public AdminException() {
		super();
	}

	public AdminException(String arg0) {
		super(arg0);
	}
}