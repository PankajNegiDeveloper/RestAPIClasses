package com.rest.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.sms.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

}
