package com.rest.sms.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rest.sms.payloads.MyCustomException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = NoResourceFoundException.class)
	public ResponseEntity<MyCustomException> notFoundException(NoResourceFoundException nfe, WebRequest we) {
		MyCustomException custom = new MyCustomException();
		custom.setDate(new Date());
		custom.setDetails(we.getDescription(false));
		custom.setMessage(nfe.getMessage());
		return new ResponseEntity<>(custom, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = ReviewAPIException.class)
	public ResponseEntity<MyCustomException> reviewApiException(NoResourceFoundException nfe, WebRequest wr){
		MyCustomException custom = new MyCustomException();
		custom.setDate(new Date());
		custom.setDetails(wr.getDescription(false));
		custom.setMessage(nfe.getMessage());
		return new ResponseEntity<>(custom, HttpStatus.BAD_REQUEST);
	}
}
