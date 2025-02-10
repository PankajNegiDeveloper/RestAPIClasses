package com.rest.sms.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.rest.sms.dto.BookDto;

public interface BookService {

	BookDto createBook(BookDto bookDto);
	List<BookDto> getAllBooks(Pageable page);
	BookDto getBookById(Integer bookid);
	BookDto updateBook(BookDto bookDto, Integer bookid);
	BookDto deleteBookById(Integer bookid);
}
