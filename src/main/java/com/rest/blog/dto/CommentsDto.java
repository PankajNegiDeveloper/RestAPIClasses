package com.rest.blog.dto;

import lombok.Data;

@Data
public class CommentsDto {
	
	private Integer commId;
	private String comment;
	private Integer blogPost;  
}
