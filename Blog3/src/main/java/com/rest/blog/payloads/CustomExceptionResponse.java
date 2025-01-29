package com.rest.blog.payloads;

import java.util.Date;

import lombok.Data;

@Data
public class CustomExceptionResponse {

	private Date date;
	private String message;
	private String details;
}
