package com.rest.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.blog.entity.BlogPost;
import com.rest.blog.entity.Comments;

import jakarta.transaction.Transactional;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer>{

	List<Comments> findByBlogPost(BlogPost blogPost);

	@Modifying
	@Transactional
	@Query("DELETE FROM Comments c WHERE c.blogPost.blogsId = :blogsId AND c.CommId = :CommId")
	void deleteByPostIdAndCommentId(@Param("blogsId") Integer blogPostId, @Param("CommId") Integer commentId);

	

}
