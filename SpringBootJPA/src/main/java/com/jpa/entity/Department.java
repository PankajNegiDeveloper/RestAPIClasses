package com.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Department {
	
	@Id
	@GeneratedValue
	private int id;
	private String deptname;

}
