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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.app.payload.BookDTO;
import com.lms.app.payload.CategoryDTO;
import com.lms.app.service.BookService;
import com.lms.app.service.CategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@RestController
@RequestMapping("/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

//	//Get All Books
//	
//	@GetMapping("/books")
//	public ResponseEntity<?> getAllBooks() {
//		List<BookDTO> books = bookService.getAllBooks();
//		return new ResponseEntity<>(books, HttpStatus.OK);
//	}



	//Add a category
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
		String msg=categoryService.addCategory(categoryDTO);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	
	//Get All Category
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllCategory(){
		List<CategoryDTO> categories =categoryService.getAllCategory();;
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	//Update a category
	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO){
		String msg=categoryService.updateCategory(categoryDTO);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	
	//Delete a category
	@DeleteMapping("{category}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCategory(@PathVariable String category){
		String msg = categoryService.deleteCategory(category);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	
	
}
