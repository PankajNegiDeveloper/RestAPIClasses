package com.rest.blog.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rest.blog.payloads.CustomExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = NoResourceFoundException.class)
	public ResponseEntity<CustomExceptionResponse> NoResourceFoundException(NoResourceFoundException rne, WebRequest wr) {
		
		CustomExceptionResponse response = new CustomExceptionResponse();
		response.setDate(new Date());
		response.setMessage(rne.getMessage());
		response.setDetails(wr.getDescription(false));
		return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
	}
}
