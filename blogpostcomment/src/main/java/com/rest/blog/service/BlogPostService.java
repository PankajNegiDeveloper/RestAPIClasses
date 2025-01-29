package com.rest.blog.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import com.rest.blog.Dto.BlogPostDto;

public interface BlogPostService {
	
	BlogPostDto createBlogPost(BlogPostDto blogPostDto);

	BlogPostDto findBlogPostById(Integer postid);

	List<BlogPostDto> findAllBlogPosts(Pageable page); 
	
	BlogPostDto updateBlogPost(BlogPostDto blogPostDto);
	
	BlogPostDto deleteBlogPostById(Integer postid);
}
