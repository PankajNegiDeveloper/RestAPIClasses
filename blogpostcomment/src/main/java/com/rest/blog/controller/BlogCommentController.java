package com.rest.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.blog.Dto.BlogCommentDto;
import com.rest.blog.service.BlogCommentService;

@RestController
@RequestMapping("/api/posts")
public class BlogCommentController {

	@Autowired
	private BlogCommentService blogCommentService;

	public BlogCommentController(BlogCommentService blogCommentService) {
		super();
		this.blogCommentService = blogCommentService;
	}

	@PostMapping("/{postid}/comments")
	public ResponseEntity<BlogCommentDto> createBlogComment(@RequestBody BlogCommentDto blogCommentDto,
			@PathVariable Integer postid) {
//		if (postid.equals(blogCommentDto.getBlogPostId())) {
		BlogCommentDto createComment = blogCommentService.createBlogComment(blogCommentDto);
		return new ResponseEntity(createComment, HttpStatus.CREATED);
//		} else {
//			return new ResponseEntity("Blog Id not present", HttpStatus.BAD_REQUEST);
//		}
	}

	@GetMapping("/{postid}/comments")
	public ResponseEntity<BlogCommentDto> findCommentsByBlogPostId(@PathVariable Integer postid) {
		List<BlogCommentDto> comments = blogCommentService.findCommentsByBlogPostId(postid);
		return new ResponseEntity(comments, HttpStatus.OK);
	}

	@GetMapping("/{postid}/comments/{commid}")
	public ResponseEntity<BlogCommentDto> findCommentByBlogPostIdAndCommentId(@PathVariable Integer postid,
			@PathVariable Integer commid) {
		List<BlogCommentDto> commentDto = blogCommentService.findCommentByBlogPostIdAndCommentId(postid, commid);
		return new ResponseEntity(commentDto, HttpStatus.OK);
	}

	@PutMapping("/{postid}/comments/{commid}")
	public ResponseEntity<BlogCommentDto> updateComment(@PathVariable Integer postid, @PathVariable Integer commid,
			@RequestBody BlogCommentDto blogCommDto) {
		BlogCommentDto updatedComment = blogCommentService.updateComment(blogCommDto, postid, commid);
		return new ResponseEntity(updatedComment, HttpStatus.OK);
	}

	@DeleteMapping("/{postid}/comments/{commid}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer postid, @PathVariable Integer commid) {
		BlogCommentDto deletedComment = blogCommentService.deleteComment(postid, commid);
		return new ResponseEntity("Comment is Deleted Successfully", HttpStatus.OK);
	}
}
