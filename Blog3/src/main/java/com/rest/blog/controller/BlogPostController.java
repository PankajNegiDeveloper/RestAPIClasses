package com.rest.blog.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.blog.dto.BlogPostDto;
import com.rest.blog.service.BlogPostService;
import com.rest.blog.util.PaginationConstants;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

	private BlogPostService blogPostService;

	public BlogPostController(BlogPostService blogPostService) {
		super();
		this.blogPostService = blogPostService;
	}

	@PostMapping
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BlogPostDto> createBlog(@RequestBody BlogPostDto blogpost) {
		BlogPostDto blogPostDto = blogPostService.createBlogPost(blogpost);
		// 201 -- new resource has been created
		// thats why using new keyword
		return new ResponseEntity<>(blogPostDto, HttpStatus.CREATED);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<BlogPostDto> findBlogPostById(@PathVariable("postId") Integer postId) {
		BlogPostDto blogPostById = blogPostService.findBlogPostById(postId);
		return new ResponseEntity(blogPostById, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<BlogPostDto> getAllBlogPost(
			@RequestParam(value = "pageNo", defaultValue = PaginationConstants.PAGENUM, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = PaginationConstants.PAGESIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "blogsId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		PageRequest page = PageRequest.of(pageNo, pageSize, sort);
		List<BlogPostDto> allBlogPosts = blogPostService.getAllBlogPost(page);
		return new ResponseEntity(allBlogPosts, HttpStatus.OK);
	}

	@PutMapping("/updateblog")
	public ResponseEntity<BlogPostDto> updateBlogPost(@RequestBody BlogPostDto blogPost) {
		BlogPostDto updateBlogPost = blogPostService.updateBlogPost(blogPost);
		return new ResponseEntity(updateBlogPost, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePostById(@PathVariable("postId") Integer postId) {
		BlogPostDto deletePostById = blogPostService.deletePostById(postId);
		return new ResponseEntity("Data Deleted successully", HttpStatus.OK);
	}
}
