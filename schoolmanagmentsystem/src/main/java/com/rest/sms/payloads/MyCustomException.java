package com.rest.sms.payloads;

import java.util.Date;

import lombok.Data;

@Data
public class MyCustomException {

	private Date date;
	private String message;
	private String details;
}
