package com.rest.blog.dto;

import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogPostDto {

	private Integer blogId;

	// title should be not not empty or null 
	@NotEmpty
	@Size(min = 5, message = "Post title should have atleast 10 character")
	private String title;
	
	@NotEmpty
	@Size(min = 4, message = "Post description should have atleast 10 character")
	private String discription;
	
	@NotEmpty
	@Size(min = 5, message = "Post content should have atleast 10 character")
	private String content;
	
//	@Pattern, used in the case of password, we can use the regx (regular expression) in it!
	
}
