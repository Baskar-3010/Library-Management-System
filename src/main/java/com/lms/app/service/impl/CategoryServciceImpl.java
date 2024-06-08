package com.lms.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.app.entities.Book;
import com.lms.app.entities.Category;
import com.lms.app.exception.BookNotFoundException;
import com.lms.app.exception.CategoryNotFoundException;
import com.lms.app.payload.CategoryDTO;
import com.lms.app.repository.CategoryRepository;
import com.lms.app.service.CategoryService;

@Transactional
@Service
public class CategoryServciceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Category findByName(String category) throws CategoryNotFoundException {
		Optional<Category> category2 = categoryRepository.getByName(category);
		if (category2.isPresent())
			return category2.get();
		else
			throw new CategoryNotFoundException("Category " + category + "not found");
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = categories.stream().map(c -> modelMapper.map(c, CategoryDTO.class))
				.collect(Collectors.toList());
		return categoryDTOs;
	}

	@Override
	public String addCategory(CategoryDTO categoryDTO) {

		Optional<Category> category = categoryRepository.getByName(categoryDTO.getName());
		if (category.isEmpty()) {
			Category saveToDB = modelMapper.map(categoryDTO, Category.class);
			categoryRepository.save(saveToDB);
			return "Category Added Successfully";
		} else
			throw new CategoryNotFoundException("Category " + category + "not found");
	}

	@Override
	public String updateCategory(CategoryDTO categoryDTO) {
		Optional<Category> category = categoryRepository.getByName(categoryDTO.getName());
		if (category.isEmpty()) {
			Category saveToDB = modelMapper.map(categoryDTO, Category.class);
			categoryRepository.save(saveToDB);
			return "Category Updated Successfully";
		} else
			throw new CategoryNotFoundException("Category " + category + "not found");
	}

	@Override
	public String deleteCategory(String category) {
		Optional<Category> categoryFromDB=categoryRepository.getByName(category);
		if(categoryFromDB.isEmpty())
			throw new CategoryNotFoundException("Category "+ category +" not Found");
		
		categoryRepository.deleteByName(category);
		return "Category Deleted Successfully";
	}

}
