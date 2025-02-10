package com.rest.sms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.sms.dto.BookDto;
import com.rest.sms.entity.Book;
import com.rest.sms.exception.NoResourceFoundException;
import com.rest.sms.repository.BookRepo;
import com.rest.sms.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	public BookServiceImpl(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	// DTO to ENTITY
	public Book mapDtoToEntity(BookDto bookDto) {
		Book book = new Book();
		book.setBookid(bookDto.getBookid());
		book.setAuthor(bookDto.getAuthor());
		book.setTitle(bookDto.getTitle());
		return book;
	}

	// ENTITY to DTO
	public BookDto mapEntityToDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setBookid(book.getBookid());
		bookDto.setAuthor(book.getAuthor());
		bookDto.setTitle(book.getTitle());
		return bookDto;
	}

	@Override
	public BookDto createBook(BookDto bookDto) {
		Book savedBook = bookRepo.save(mapDtoToEntity(bookDto));
		return mapEntityToDto(savedBook);
	}

	@Override
	public List<BookDto> getAllBooks(Pageable page) {
		Page<Book> allBooks = bookRepo.findAll(page);
		List<Book> content = allBooks.getContent();
		return content.stream().map(books -> mapEntityToDto(books)).toList();
	}

	@Override
	public BookDto getBookById(Integer bookid) {
		Optional<Book> byId = bookRepo.findById(bookid);
		Book book = byId.orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));
		return mapEntityToDto(book);
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Integer bookid) {
		Book book = bookRepo.findById(bookid).orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));
		book.setAuthor(bookDto.getAuthor());
		book.setTitle(bookDto.getTitle());
		bookRepo.save(mapDtoToEntity(bookDto));
		return mapEntityToDto(book);
	}

	@Override
	public BookDto deleteBookById(Integer bookid) {
		Book book = bookRepo.findById(bookid).orElseThrow(() -> new NoResourceFoundException("Book", "ID", bookid));
		bookRepo.delete(book);
		return null;
	}
}
