package com.rest.sms.serviceimpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rest.sms.dto.ReviewDto;
import com.rest.sms.entity.Book;
import com.rest.sms.entity.Review;
import com.rest.sms.exception.NoResourceFoundException;
import com.rest.sms.exception.ReviewAPIException;
import com.rest.sms.repository.BookRepo;
import com.rest.sms.repository.ReviewRepo;
import com.rest.sms.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepo reviewRepo;
	private BookRepo bookRepo;

	public ReviewServiceImpl(ReviewRepo reviewRepo, BookRepo bookRepo) {
		super();
		this.reviewRepo = reviewRepo;
		this.bookRepo = bookRepo;
	}

	// DTO to Entity
	public Review mapDtoToEntity(ReviewDto reviewDto, Book book) {
		Review review = new Review();
		review.setReviewid(reviewDto.getReviewid());
		review.setRatings(reviewDto.getRatings());
		review.setComment(reviewDto.getComment());
		review.setBook(book);
		return review;
	}

	// Entity to Dto
	public ReviewDto mapEntityToDto(Review review) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setReviewid(review.getReviewid());
		reviewDto.setRatings(review.getRatings());
		reviewDto.setComment(review.getComment());
		reviewDto.setBookid(review.getBook().getBookid());
		return reviewDto;
	}

	@Override
	public ReviewDto createReviewById(ReviewDto reviewDto, Integer bookid) {
		Book book = bookRepo.findById(bookid).orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));

		if (!bookid.equals(reviewDto.getBookid())) {
			throw new ReviewAPIException(HttpStatus.BAD_REQUEST,
					"Book id does not match with the end point id: " + bookid);
		}
		// converting DTO TO ENTITY
		Review review = mapDtoToEntity(reviewDto, book);
		Review createdReview = reviewRepo.save(review);
		return mapEntityToDto(createdReview);
	}

	@Override
	public ReviewDto getReviewById(Integer reviewid) {
		Review review = reviewRepo.findById(reviewid)
				.orElseThrow(() -> new NoResourceFoundException("Review", "Id", reviewid));
		return mapEntityToDto(review);
	}

	@Override
	public List<ReviewDto> getReviewByBookId(Integer bookid) {
		Book book = bookRepo.findById(bookid).orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));
		List<Review> byBook = reviewRepo.findByBook(book);
		return byBook.stream().map(reviews -> mapEntityToDto(reviews)).toList();
	}

	@Override
	public List<ReviewDto> getReviewsByBookAndReviewId(Integer bookid, Integer reviewid) {
		Book book = bookRepo.findById(bookid).orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));
		Review review = reviewRepo.findById(reviewid)
				.orElseThrow(() -> new NoResourceFoundException("Review", "ID", reviewid));

		List<Review> byBookAndReviewid = reviewRepo.findByBookAndReviewid(book, reviewid);

		if (byBookAndReviewid.isEmpty()) {
			throw new ReviewAPIException(HttpStatus.BAD_REQUEST, "Reviews are not present for this book id: " + bookid);
		}
		return byBookAndReviewid.stream().map(reviews -> mapEntityToDto(reviews)).toList();
	}

	@Override
	public ReviewDto updateReview(ReviewDto reviewDto, Integer bookid, Integer reviewid) {
		Book book = bookRepo.findById(bookid).orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));
		Review review = reviewRepo.findById(reviewid)
				.orElseThrow(() -> new NoResourceFoundException("Review", "ID", reviewid));

		if (!review.getBook().getBookid().equals(bookid)) {
			throw new ReviewAPIException(HttpStatus.BAD_REQUEST,
					"Review id " + reviewid + " does not belong to the book id: " + bookid);
		}

		review.setRatings(reviewDto.getRatings());
		review.setComment(reviewDto.getComment());

		Review updatedReview = reviewRepo.save(review);
		return mapEntityToDto(updatedReview);
	}

}
