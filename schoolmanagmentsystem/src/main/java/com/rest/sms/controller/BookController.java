package com.rest.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.sms.dto.BookDto;
import com.rest.sms.service.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@PostMapping("/create")
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
		BookDto bookCreated = bookService.createBook(bookDto);
		return new ResponseEntity<>(bookCreated, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<BookDto> getALlBooks(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "6", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		PageRequest page = PageRequest.of(pageNo, pageSize, sort);

		List<BookDto> allBooks = bookService.getAllBooks(page);
		return new ResponseEntity(allBooks, HttpStatus.OK);
	}

	@GetMapping("/{bookid}")
	public ResponseEntity<BookDto> getBookById(@PathVariable Integer bookid) {
		BookDto bookById = bookService.getBookById(bookid);
		return new ResponseEntity<>(bookById, HttpStatus.OK);
	}

	@PutMapping("/update/{bookid}")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable Integer bookid) {
		BookDto updateBook = bookService.updateBook(bookDto, bookid);
		return new ResponseEntity<>(updateBook, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{bookid}")
	public ResponseEntity<String> deleteBookById(@PathVariable Integer bookid) {
		bookService.deleteBookById(bookid);
		return new ResponseEntity<>("Book id " + bookid + " is deleted Succefully.", HttpStatus.OK);
	}
}
