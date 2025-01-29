package com.rest.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="BlogComment_tb")
public class BlogComment {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commid;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "postid")
	private BlogPost blogPost;
}
