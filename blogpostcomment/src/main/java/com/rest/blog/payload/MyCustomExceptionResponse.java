package com.rest.blog.payload;

import java.util.Date;

import lombok.Data;

@Data
public class MyCustomExceptionResponse {

	private Date date;
	private String message;
	private String details;
}
