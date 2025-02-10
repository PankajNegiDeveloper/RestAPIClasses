package com.rest.sms.dto;

import lombok.Data;

@Data
public class ReviewDto {

	private Integer reviewid;
	private String ratings;
	private String comment;
	private Integer bookid;
}
