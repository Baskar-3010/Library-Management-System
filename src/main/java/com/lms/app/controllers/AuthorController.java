package com.lms.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.app.payload.AuthorDTO;
import com.lms.app.service.AuthorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/authors")
@SecurityRequirement(name = "bearerAuth")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<?> getAllAuthors() {
		List<AuthorDTO> authors = authorService.getAllAuthors();
		return new ResponseEntity<>(authors, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> saveAuthor(@RequestBody AuthorDTO authorDTO) {
		String msg = authorService.saveAuthor(authorDTO);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/{author}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteAuthor(@PathVariable String author){
		String res= authorService.deleteAuthor(author);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
