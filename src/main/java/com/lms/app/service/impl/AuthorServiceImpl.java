package com.lms.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.app.entities.Author;
import com.lms.app.entities.Book;
import com.lms.app.exception.AuthorNotFoundException;
import com.lms.app.payload.AuthorDTO;
import com.lms.app.payload.BookDTO;
import com.lms.app.repository.AuthorRepository;
import com.lms.app.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AuthorDTO> getAllAuthors() {
		List<Author> authors = authorRepository.findAll();
		List<AuthorDTO> authorDTOs = authors.stream().map(author -> modelMapper.map(author, AuthorDTO.class))
				.collect(Collectors.toList());
		return authorDTOs;
	}

	@Override
	public String saveAuthor(AuthorDTO authorDTO) {
		Optional<Author> authorFromDB = authorRepository.getByName(authorDTO.getName());
		if(authorFromDB.isEmpty()) {
			authorRepository.save(modelMapper.map(authorDTO , Author.class));
			return "Author Saved Successfully";
		}
		return "Author already Saved";
	}

	@Override
	public List<BookDTO> getAllBooksByAuthor(String author) {
		Optional<Author> authorFromDB = authorRepository.getByName(author);
		if(authorFromDB.isPresent()) {
			List<Book> books= authorFromDB.get().getBooks();
			List<BookDTO> bookDTOs = books.stream()
					.map(book->modelMapper.map(book, BookDTO.class))
					.collect(Collectors.toList());
			return bookDTOs;
		}
		throw new AuthorNotFoundException("Author "+ author+" not Found");
	}

	@Override
	public String deleteAuthor(String author) {
			Optional<Author> authorFromDB = authorRepository.getByName(author);
			if(authorFromDB.isEmpty()) {
				throw new AuthorNotFoundException("Author "+ author+" not Found");
			}
		authorRepository.deleteByName(author);
		return "Author Deleted Successfully";
	}

}
