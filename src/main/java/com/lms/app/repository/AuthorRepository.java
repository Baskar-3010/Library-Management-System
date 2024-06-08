package com.lms.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	//Optional<Author> getAuthorByName(String name);

	boolean existsByName(String author);

	Optional<Author> getByName(String category);

	void deleteByName(String author);

	void deleteAllByName(String author);

}
