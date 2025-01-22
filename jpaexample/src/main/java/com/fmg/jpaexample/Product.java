package com.fmg.jpaexample;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prod_db")
public class Product {

	@Id
	@GeneratedValue()
	@Column(name = "prod_id")
	private int id;
	@Column(name = "prod_name")
	private String name;
	@Column(name = "prod_dept")
	private String dept;

	public Product() {
		super();
	}

	public Product(int id, String name, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return dept;
	}

	public void setPrice(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}
}
