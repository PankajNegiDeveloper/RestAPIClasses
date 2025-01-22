package com.rest.blog.service;

import java.util.List;

import com.rest.blog.dto.CommentsDto;

public interface CommentsService {
	// making this loosly coupling, we are creating service layer interface

	CommentsDto createComment(CommentsDto commentsDto);

	CommentsDto findCommentById(Integer id);

	List<CommentsDto> getAllComments(Integer postId);

	CommentsDto updateComment(CommentsDto commentDto);

	void deleteByPostIdAndCommentId(Integer postId, Integer id);
}
