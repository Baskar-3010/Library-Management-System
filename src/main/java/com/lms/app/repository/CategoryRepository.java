package com.lms.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	//Optional<Category> getCategoryByName(String category);

	//Boolean deleteCategoryByName(String category);

	long deleteByName(String category);

	Optional<Category> getByName(String category);

}
