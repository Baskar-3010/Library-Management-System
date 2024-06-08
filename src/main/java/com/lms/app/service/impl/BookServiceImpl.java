package com.lms.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lms.app.entities.Author;
import com.lms.app.entities.Book;
import com.lms.app.entities.Category;
import com.lms.app.exception.BookNotFoundException;
import com.lms.app.payload.BookDTO;
import com.lms.app.repository.AuthorRepository;
import com.lms.app.repository.BookRepository;
import com.lms.app.service.BookService;
import com.lms.app.service.CategoryService;

@Transactional
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<BookDTO> getAllBooks() {

		List<Book> books = bookRepository.findAll();
		List<BookDTO> booksDtos = books.stream().map(book -> modelMapper.map(book, BookDTO.class))
				.collect(Collectors.toList());
		return booksDtos;
	}

	@Override
	public List<BookDTO> getAllBooksByCategory(String category) {
		Category categoryFromDB = categoryService.findByName(category);
		List<Book> books = categoryFromDB.getBooks();
	   
		List<BookDTO> booksDtos = books.stream().map(book -> modelMapper.map(book, BookDTO.class))
				.collect(Collectors.toList());
		return booksDtos;
	}

	@Override
	public String addBook(BookDTO bookDTO, String category) {
		Category categoryFromDB = categoryService.findByName(category);
		Book book = modelMapper.map(bookDTO, Book.class);
		book.setCategory(categoryFromDB);
		Optional<Author> authorFromDB = authorRepository.getByName(bookDTO.getAuthor().getName());
		if (authorFromDB.isPresent()) {
			book.setAuthor(authorFromDB.get());
			bookRepository.save(book);

		} else {
			return "Add Author Details First";
		}
		// categoryFromDB.setBooks(categoryFromDB.getBooks().add(book));;
		bookRepository.save(book);
		return "Book added Successfully";
	}

	@Override
	public String updateBook(BookDTO bookDTO, String category) {
		Category categoryFromDB = categoryService.findByName(category);
		Book book = modelMapper.map(bookDTO, Book.class);
		book.setCategory(categoryFromDB);
		// categoryFromDB.setBooks(categoryFromDB.getBooks().add(book));;
		bookRepository.save(book);
		return "Book Updated Successfully";
	}

	@Override
	public String deleteBook(BookDTO bookDTO, String category) {
		Optional<Book> bookFromDB = bookRepository.getBookByName(bookDTO.getName());
		if (bookFromDB.isEmpty())
			throw new BookNotFoundException("Book not Found");
		Book book = modelMapper.map(bookDTO, Book.class);
		bookRepository.deleteByName(book.getName());
		return "Book Deleted Successfully ";
	}

}
