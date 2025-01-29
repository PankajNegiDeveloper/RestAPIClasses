package com.rest.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.blog.Dto.BlogPostDto;
import com.rest.blog.service.BlogPostService;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

	@Autowired
	private BlogPostService blogPostService;

	public BlogPostController(BlogPostService blogPostService) {
		super();
		this.blogPostService = blogPostService;
	}

	@PostMapping("/create")
	public ResponseEntity<BlogPostDto> createBlogPost(@RequestBody BlogPostDto blogPost) {
		BlogPostDto blogPostDto = blogPostService.createBlogPost(blogPost);
		return new ResponseEntity(blogPostDto, HttpStatus.OK);
	}

	@GetMapping("/{postid}")
	public ResponseEntity<BlogPostDto> findBlogPostById(@PathVariable("postid") Integer postid) {
		BlogPostDto blogPostDto = blogPostService.findBlogPostById(postid);
		return new ResponseEntity(blogPostDto, HttpStatus.OK);
	}
 
	@GetMapping
	public ResponseEntity<BlogPostDto> findAllBlogPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postid", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		PageRequest page = PageRequest.of(pageNo, pageSize, sort);
		List<BlogPostDto> allBlogPosts = blogPostService.findAllBlogPosts(page);
		return new ResponseEntity(allBlogPosts, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<BlogPostDto> updateBlogPost(@RequestBody BlogPostDto blogPostDto) {
		BlogPostDto updateBlog = blogPostService.updateBlogPost(blogPostDto);
		return new ResponseEntity(updateBlog, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<BlogPostDto> deleteBlogPostById(@PathVariable Integer postId) {
		BlogPostDto deleteBlogPost = blogPostService.deleteBlogPostById(postId);
		return new ResponseEntity("Data deleted successfully", HttpStatus.OK);
	}
}
