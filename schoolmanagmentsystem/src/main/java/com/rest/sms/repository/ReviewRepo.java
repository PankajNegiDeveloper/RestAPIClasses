package com.rest.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.sms.entity.Book;
import com.rest.sms.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer>{

	List<Review> findByBook(Book book);

	List<Review> findByBookAndReviewid(Book book, Integer reviewid);

}
