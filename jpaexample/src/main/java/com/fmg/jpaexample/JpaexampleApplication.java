package com.fmg.jpaexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaexampleApplication implements CommandLineRunner {

	@Autowired
	ProductRepo prodRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaexampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product product = new Product(1, "Pen", "school");
		prodRepo.save(product);
		
	}

}
