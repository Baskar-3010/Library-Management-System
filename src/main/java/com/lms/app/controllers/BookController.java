package com.lms.app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lms.app.entities.FileDB;
import com.lms.app.payload.BookDTO;
import com.lms.app.service.AuthorService;
import com.lms.app.service.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/books")
@SecurityRequirement(name = "bearerAuth")
public class BookController {

	@Autowired
	private BookService bookService;

//	@Autowired
//	private CategoryService categoryService;

	@Autowired
	private AuthorService authorService;

	// Get All Books
	@GetMapping("/books")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<?> getAllBooks() {
		List<BookDTO> books = bookService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	// Get all Book By Author
	@GetMapping("/author/{author}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<?> getAllBooksByAuthor(@PathVariable String author) {
		List<BookDTO> bookDTOs = authorService.getAllBooksByAuthor(author);
		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
	}

	// Get all Book By Category
	@GetMapping("/category/{category}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<?> getAllBooksByCategory(@PathVariable String category) {
		List<BookDTO> books = bookService.getAllBooksByCategory(category);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	// Add a book to a category
	@PostMapping("/category/{category}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addBook(@RequestPart("body") BookDTO bookDTO,@RequestParam("file") MultipartFile file,@PathVariable String category) throws IOException {
		System.out.println(file.getContentType());
		bookDTO.setFileDB(new FileDB(file.getOriginalFilename(),file.getContentType(),file.getBytes()));
		String response  = bookService.addBook(bookDTO, category);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	

	// Update a book in a category
	@PutMapping("/category/{category}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO, @PathVariable String category) {
		
		String response = bookService.updateBook(bookDTO, category);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Delete a book in a category
	@DeleteMapping("/category/{category}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteBook(@RequestBody BookDTO bookDTO, @PathVariable String category) {
		String response = bookService.deleteBook(bookDTO, category);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

//	//Add a book to a category
//	@PostMapping("/category/{category}")
//	public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO,@PathVariable String category){
//		String response = bookService.addBook(bookDTO,category);
//		return new ResponseEntity<>(response,HttpStatus.CREATED);
//	}

//	//Update a book in a category
//	@PutMapping("/category/{category}")
//	public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO,@PathVariable String category){
//		String response = bookService.updateBook(bookDTO,category);
//		return new ResponseEntity<>(response,HttpStatus.CREATED);
//	}

//	//Delete a book in a category
//	@PostMapping("/category/{category}")
//	public ResponseEntity<?> deleteBook(@RequestBody BookDTO bookDTO,@PathVariable String category){
//		String response = bookService.deleteBook(bookDTO,category);
//		return new ResponseEntity<>(response,HttpStatus.CREATED);
//	}

}
