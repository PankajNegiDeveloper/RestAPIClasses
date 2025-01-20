package com.rest.blog.service;

import java.util.List;

import com.rest.blog.dto.BlogPostDto;
import com.rest.blog.entity.BlogPost;

public interface BlogPostService {
//making this loosly coupling, we are creating service layer interface

	BlogPostDto createBlogPost(BlogPostDto blogPostDto);

	BlogPostDto findBlogPostById(Integer id);
	
	List<BlogPostDto> getAllBlogPost();
	
	BlogPostDto updateBlogPost (BlogPostDto blogPostDto);
	
	BlogPostDto deletePostById (Integer id);
}
