package com.lms.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import lombok.val;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {CategoryNotFoundException.class})
	public ResponseEntity<Object> resourceNotFoundException(CategoryNotFoundException e) {
		ApiException apiException= new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(apiException,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = {BookNotFoundException.class})
	public ResponseEntity<Object> bookNotFoundException(BookNotFoundException e) {
		ApiException apiException= new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(apiException,HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = {AuthorNotFoundException.class})
	public ResponseEntity<Object> authorNotFoundException(AuthorNotFoundException e) {
		ApiException apiException= new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(apiException,HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(value = {FileNotFoundException.class})
	public ResponseEntity<Object> fileNotFoundException(FileNotFoundException e) {
		ApiException apiException= new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(apiException,HttpStatus.BAD_REQUEST);

	}
	  @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ApiException("File too large!",exc,HttpStatus.BAD_REQUEST));
	  }
}




