package com.rest.sms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.sms.dto.ReviewDto;
import com.rest.sms.entity.Review;
import com.rest.sms.service.ReviewService;

@RestController
@RequestMapping("/api/books")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

	@PostMapping("/reviewcreate/{bookid}")
	public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable Integer bookid) {
		ReviewDto reviewById = reviewService.createReviewById(reviewDto, bookid);
		return new ResponseEntity<>(reviewById, HttpStatus.CREATED);
	}

	@GetMapping("/reviews/{reviewid}")
	public ResponseEntity<ReviewDto> getALLreviews(@PathVariable Integer reviewid) {
		ReviewDto reviewById = reviewService.getReviewById(reviewid);
		return new ResponseEntity(reviewById, HttpStatus.OK);
	}

	@GetMapping("/{bookid}/reviews")
	public ResponseEntity<ReviewDto> getReviewByBookId(@PathVariable Integer bookid) {
		List<ReviewDto> reviewById = reviewService.getReviewByBookId(bookid);
		return new ResponseEntity(reviewById, HttpStatus.OK);
	}

	@GetMapping("/{bookid}/reviews/{reviewid}")
	public ResponseEntity<ReviewDto> getReviewsByBookAndReviewId(@PathVariable Integer bookid,
			@PathVariable Integer reviewid) {
		List<ReviewDto> reviewsByBookAndReviewId = reviewService.getReviewsByBookAndReviewId(bookid, reviewid);
		return new ResponseEntity(reviewsByBookAndReviewId, HttpStatus.OK);
	}

	@PutMapping("/{bookid}/update/{reviewid}")
	public ResponseEntity<ReviewDto> updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Integer bookid,
			@PathVariable Integer reviewid) {
		ReviewDto updateReview = reviewService.updateReview(reviewDto, bookid, reviewid);
		return new ResponseEntity<>(updateReview, HttpStatus.OK);
	}
}
