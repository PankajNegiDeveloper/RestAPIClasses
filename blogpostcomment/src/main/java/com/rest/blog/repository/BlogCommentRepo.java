package com.rest.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rest.blog.entity.BlogComment;
import com.rest.blog.entity.BlogPost;

import jakarta.transaction.Transactional;

@Repository
public interface BlogCommentRepo extends JpaRepository<BlogComment, Integer> {

	List<BlogComment> findByBlogPost(BlogPost blogpostid);

	List<BlogComment> findByBlogPostAndCommid(BlogPost blogPost, Integer commid);

	@Transactional
	@Modifying
	@Query("DELETE FROM BlogComment bc WHERE bc.commid = :commentId")
	void deleteByCommid(Integer commentId);

}
