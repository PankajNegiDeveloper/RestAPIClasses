package com.rest.blog.service;

import java.util.List;

import com.rest.blog.Dto.BlogCommentDto;

public interface BlogCommentService {

	BlogCommentDto createBlogComment(BlogCommentDto blogCommentDto);
	
	List<BlogCommentDto> findCommentsByBlogPostId(Integer postid);
	
	List<BlogCommentDto> findCommentByBlogPostIdAndCommentId(Integer postid, Integer commid);
	
	BlogCommentDto updateComment(BlogCommentDto blogCommentDto, Integer postid, Integer commid);
	
	BlogCommentDto deleteComment(Integer postid, Integer commid);
}
