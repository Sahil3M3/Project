package com.app.sunbeam.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotFoundException extends RuntimeException {
	


	public UserNotFoundException(String message) {
		super(message);
	}


}
