package com.rest.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.blog.entity.BlogPost;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost, Integer>{

}
