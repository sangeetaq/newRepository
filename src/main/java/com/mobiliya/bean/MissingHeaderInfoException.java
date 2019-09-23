package com.mobiliya.bean;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Exception class for Missing Header
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingHeaderInfoException extends RuntimeException {

	public MissingHeaderInfoException(String message) {
		super(message);
	}
}
