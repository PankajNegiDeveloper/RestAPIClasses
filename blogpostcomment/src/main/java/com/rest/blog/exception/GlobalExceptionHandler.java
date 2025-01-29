package com.rest.blog.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rest.blog.payload.MyCustomExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = NoResourceFoundException.class)
	public ResponseEntity<MyCustomExceptionResponse> noResourceFoundException(NoResourceFoundException rne, WebRequest wr) {
		MyCustomExceptionResponse response = new MyCustomExceptionResponse();
		response.setDate(new Date());
		response.setMessage(rne.getMessage());
		response.setDetails(wr.getDescription(false));
		return new ResponseEntity(response, HttpStatus.NOT_FOUND);
	}
}
