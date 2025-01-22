package com.rest.blog.dto;

import lombok.Data;

@Data
public class BlogPostDto {

	private Integer blogId;
	private String title;
	private String discription;
	private String content;
}
