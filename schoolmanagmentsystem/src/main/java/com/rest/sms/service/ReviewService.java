package com.rest.sms.service;

import java.util.List;

import com.rest.sms.dto.ReviewDto;

public interface ReviewService {
	
	ReviewDto createReviewById(ReviewDto reviewDto, Integer bookid);
	ReviewDto getReviewById(Integer reviewid);
	List<ReviewDto> getReviewByBookId(Integer bookid);
	List<ReviewDto> getReviewsByBookAndReviewId(Integer bookid, Integer reviewid);
	
	ReviewDto updateReview(ReviewDto reviewDto, Integer bookid, Integer reviewid);
	
}
