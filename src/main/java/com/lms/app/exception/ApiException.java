package com.lms.app.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class ApiException {
	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	
}
