package com.rest.blog.Dto;

import lombok.Data;

@Data
public class BlogCommentDto {

	private Integer commid;
	private String comment;
	private Integer blogPostId;
}
