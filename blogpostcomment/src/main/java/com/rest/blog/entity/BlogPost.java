package com.rest.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BlogPost_table")
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postid;
	private String title;
	private String description;
	private String content;

	@OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<BlogComment> comments = new ArrayList<>();
}
