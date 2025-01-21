package com.rest.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.blog.dto.CommentsDto;
import com.rest.blog.entity.Comments;
import com.rest.blog.service.CommentsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/posts")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;

	public CommentsController(CommentsService commentsService) {
		super();
		this.commentsService = commentsService;
	}

	// creating comment
	@PostMapping("/comment")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto comments) {
		CommentsDto commentsDto = commentsService.createComment(comments);
		return new ResponseEntity<>(commentsDto, HttpStatus.CREATED);
	}

	@GetMapping("/{commentid}/comments")
	public ResponseEntity<Comments> findCommentById(@PathVariable("commentid") Integer commentId) {
		CommentsDto commentById = commentsService.findCommentById(commentId);
		return new ResponseEntity(commentById, HttpStatus.OK);
	}

	@GetMapping("/{postId}/allcomments")
	public ResponseEntity<List<CommentsDto>> getAllComments(@PathVariable("postId") Integer postId) {
		List<CommentsDto> allComments = commentsService.getAllComments(postId);
		return new ResponseEntity(allComments, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<CommentsDto> updateComments(@RequestBody CommentsDto comments) {
		CommentsDto updateComment = commentsService.updateComment(comments);
		return new ResponseEntity(updateComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}/comments/{id}")
	public ResponseEntity<CommentsDto> updateComments(@PathVariable("postId") Integer postId, @PathVariable("id") Integer id) {
		 commentsService.deleteByPostIdAndCommentId(postId,id);
		return new ResponseEntity("Deleted successfully", HttpStatus.OK);
	}
	
	
}
