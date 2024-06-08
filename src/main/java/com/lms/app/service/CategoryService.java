package com.lms.app.service;

import java.util.List;

import com.lms.app.entities.Category;
import com.lms.app.payload.CategoryDTO;

public interface CategoryService {

	Category findByName(String category);

	String addCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> getAllCategory();

	String updateCategory(CategoryDTO categoryDTO);

	String deleteCategory(String category);

}
