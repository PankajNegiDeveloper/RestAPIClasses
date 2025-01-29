package com.rest.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.blog.Dto.BlogCommentDto;
import com.rest.blog.Dto.BlogPostDto;
import com.rest.blog.entity.BlogComment;
import com.rest.blog.entity.BlogPost;
import com.rest.blog.exception.NoResourceFoundException;
import com.rest.blog.repository.BlogCommentRepo;
import com.rest.blog.repository.BlogPostRepo;
import com.rest.blog.service.BlogCommentService;
import com.rest.blog.service.BlogPostService;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	private BlogCommentRepo blogCommentRepo;
	private ModelMapper mapper;
	private BlogPostService blogPostService;

	private BlogPostRepo blogPostRepo;

	@Autowired
	public BlogCommentServiceImpl(BlogCommentRepo blogCommentRepo, BlogPostServiceImpl blogPostService,
			ModelMapper mapper, BlogPostRepo blogPostRepo) {
		super();
		this.blogCommentRepo = blogCommentRepo;
		this.blogPostService = blogPostService;
		this.mapper = mapper;
		this.blogPostRepo = blogPostRepo;
	}

	// ********************************************************
	// Dto to Entity
	public BlogComment mapDtoToEntity(BlogCommentDto blogCommentDto) {
		BlogPostDto blogPost = blogPostService.findBlogPostById(blogCommentDto.getBlogPostId());
		BlogComment blogComment = mapper.map(blogCommentDto, BlogComment.class);
		return blogComment;
	}

	// Entity to Dto
	public BlogCommentDto mapEntityToDto(BlogComment blogComment) {
		BlogCommentDto blogCommentDto = mapper.map(blogComment, BlogCommentDto.class);
		return blogCommentDto;
	}
	// ********************************************************

	// creating comments
	@Override
	public BlogCommentDto createBlogComment(BlogCommentDto blogCommentDto) {
		BlogComment blogComment = blogCommentRepo.save(mapDtoToEntity(blogCommentDto));
		return mapEntityToDto(blogComment);
	}

	// finding the comments by BlogPostId
	@Override
	public List<BlogCommentDto> findCommentsByBlogPostId(Integer postid) {
		// BlogPost blogPost = blogPostService.findBlogPostById(postid);
		BlogPost blogPost = blogPostRepo.findById(postid)
				.orElseThrow(() -> new NoResourceFoundException("BlogPost", "ID", postid));
		List<BlogComment> comments = blogCommentRepo.findByBlogPost(blogPost);
		return comments.stream().map(comment -> mapEntityToDto(comment)).toList();
	}

	@Override
	public List<BlogCommentDto> findCommentByBlogPostIdAndCommentId(Integer postid, Integer commid) {
		BlogPost blogPost = blogPostRepo.findById(postid)
				.orElseThrow(() -> new NoResourceFoundException("BlogPost", "ID", postid));
		List<BlogComment> comments = blogCommentRepo.findByBlogPostAndCommid(blogPost, commid);
		if (comments.isEmpty()) {
			throw new NoResourceFoundException("BlogComment", "ID", commid);
		}
		return comments.stream().map(comment -> mapEntityToDto(comment)).toList();
	}

	// updating the commid
	@Override
	public BlogCommentDto updateComment(BlogCommentDto blogCommentDto, Integer postid, Integer commid) {
		BlogPost blogPost = blogPostRepo.findById(postid)
				.orElseThrow(() -> new NoResourceFoundException("BlogPost", "ID", postid));

		BlogComment comment = blogCommentRepo.findById(commid)
				.orElseThrow(() -> new NoResourceFoundException("BlogComment", "ID", commid));

		// Check if the comment belongs to the specified blog post
		if (!comment.getBlogPost().getPostid().equals(postid)) {
			throw new IllegalArgumentException("Comment does not belong to the specified BlogPost");
		}

		// update the comment text
		comment.setComment(blogCommentDto.getComment());

		// Update the comment entity
		BlogComment updatedComment = blogCommentRepo.save(comment);
		return mapEntityToDto(updatedComment);
	}

	// delete comment by Id
	@Override
	public BlogCommentDto deleteComment(Integer postid, Integer commid) {
//		BlogPost blogPost = blogPostRepo.findById(postid)
//				.orElseThrow(() -> new NoResourceFoundException("BlogPost", "post_ID", postid));

		// Ensures the comment exists
		BlogComment comment = blogCommentRepo.findById(commid)
				.orElseThrow(() -> new NoResourceFoundException("BlogComment", "ID", commid));

		// Now delete the comment
		blogCommentRepo.deleteByCommid(comment.getCommid());
		return mapEntityToDto(comment);
	}
}
