package com.rest.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.blog.dto.BlogPostDto;
import com.rest.blog.dto.CommentsDto;
import com.rest.blog.entity.BlogPost;
import com.rest.blog.entity.Comments;
import com.rest.blog.exception.NoResourceFoundException;
import com.rest.blog.repository.BlogPostRepo;
import com.rest.blog.service.BlogPostService;

@Service
public class BlogPostServiceImpl implements BlogPostService {

	private BlogPostRepo blogPostRepo;
	
	@Autowired
	private CommentsServiceImpl commentServiceImpl;

	@Autowired
	private BlogPostServiceImpl(BlogPostRepo blogPostRepo) {
		super();
		this.blogPostRepo = blogPostRepo;
	}

	// *****************************************************************
	// Logic for changing DtoToEntity and EntityToDto
	private BlogPost mapDtoToEntity(BlogPostDto blogPostDto) {

		// Converts a BlogPostDto into a BlogPost entity.
		// because the repository layer works with entities, not DTOs
		BlogPost blogPost = new BlogPost();
		blogPost.setContent(blogPostDto.getContent());
		blogPost.setDiscription(blogPostDto.getDiscription());
		blogPost.setTitle(blogPostDto.getTitle());
		blogPost.setBlogsId(blogPostDto.getBlogId());
		return blogPost;
	}

	private BlogPostDto mapEntityToDto(BlogPost blogpost) {

		// Converting a BlogPost(entity) to BlogPostDto
		// because controller only understands Dto and not the entity
		BlogPostDto blogPostDto = new BlogPostDto();
		blogPostDto.setContent(blogpost.getContent());
		blogPostDto.setDiscription(blogpost.getDiscription());
		blogPostDto.setTitle(blogpost.getTitle());
		blogPostDto.setBlogId(blogpost.getBlogsId());

		// This is done so that when we check all blog posts, we can see all comments
		// related to it
		List<Comments> list = blogpost.getComments();
		List<CommentsDto> commentsDtoList = list.stream().map(comment -> commentServiceImpl.mapEntityToDto(comment)).toList();
		blogPostDto.setComments(commentsDtoList);

		return blogPostDto;
	}
	// *****************************************************************

	// Creating Blogs here
	@Override
	public BlogPostDto createBlogPost(BlogPostDto blogpostdto) {
		BlogPost blogPost = blogPostRepo.save(mapDtoToEntity(blogpostdto));
		return mapEntityToDto(blogPost);
	}

	// finding by Id
	@Override
	public BlogPostDto findBlogPostById(Integer id) {
		Optional<BlogPost> findById = blogPostRepo.findById(id);
		BlogPost blogPost = findById.orElseThrow(() -> new NoResourceFoundException("BlogPost", "Id", id));
		return mapEntityToDto(blogPost);
	}

	// Find all records
	@Override
	// Pageable object here contains information about the requested page, size, and sorting.
	public List<BlogPostDto> getAllBlogPost(Pageable page) {
		Page<BlogPost> findAll = blogPostRepo.findAll(page);
		// List<BlogPost> findAll = blogPostRepo.findAll();
		List<BlogPost> content = findAll.getContent();
		return content.stream().map(blogPost -> mapEntityToDto(blogPost)).toList();
	}

	// Update the records
	@Override
	public BlogPostDto updateBlogPost(BlogPostDto blogPostDto) {
		Optional<BlogPost> findById = blogPostRepo.findById(blogPostDto.getBlogId());
		BlogPost blogs = findById
				.orElseThrow(() -> new NoResourceFoundException("BlogPost", "Id", blogPostDto.getBlogId()));
		blogs = blogPostRepo.save(mapDtoToEntity(blogPostDto));
		return mapEntityToDto(blogs);

//		if(findById.isPresent()) {
//			blogPostUpdate=blogPostRepo.save(mapDtoToEntity(blogPostDto));
//		}else {
//			throw new NoResourceFoundException("BlogPost", "Id", blogPostDto.getBlogId());
//		}
	}

	// delete the Post
	@Override
	public BlogPostDto deletePostById(Integer id) {
		BlogPost blogPost = blogPostRepo.findById(id)
				.orElseThrow(() -> new NoResourceFoundException("BlogPost", "Id", id));
		blogPostRepo.deleteById(id);
		return mapEntityToDto(blogPost);
	}
}
