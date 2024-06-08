package com.lms.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	Optional<Book> getBookByName(String name);

	void deleteByName(String name);

}
