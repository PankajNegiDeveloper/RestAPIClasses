package com.rest.blog.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.rest.blog.dto.BlogPostDto;

public interface BlogPostService {
//making this loosly coupling, we are creating service layer interface

	BlogPostDto createBlogPost(BlogPostDto blogPostDto);

	BlogPostDto findBlogPostById(Integer id);
	
	List<BlogPostDto> getAllBlogPost(Pageable page);
	
	BlogPostDto updateBlogPost (BlogPostDto blogPostDto);
	
	BlogPostDto deletePostById (Integer id);

//	List<BlogPostDto> getAllBlogPost(
//			org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable page);
}
