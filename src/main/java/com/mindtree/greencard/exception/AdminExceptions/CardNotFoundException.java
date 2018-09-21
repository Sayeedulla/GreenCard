package com.mindtree.greencard.exception.AdminExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException{

	public CardNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
