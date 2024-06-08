package com.lms.app.service;

import java.util.List;

import com.lms.app.payload.BookDTO;

public interface BookService {

	List<BookDTO> getAllBooks();

	List<BookDTO> getAllBooksByCategory(String category);

	String addBook(BookDTO bookDTO,String  category);

	String updateBook(BookDTO bookDTO, String category);

	String deleteBook(BookDTO bookDTO, String category);
	
}
