package com.lms.app.service;

import java.util.List;

import com.lms.app.payload.AuthorDTO;
import com.lms.app.payload.BookDTO;

public interface AuthorService {

	List<AuthorDTO> getAllAuthors();

	String saveAuthor(AuthorDTO authorDTO);

	List<BookDTO> getAllBooksByAuthor(String author);

	String deleteAuthor(String author);

}
