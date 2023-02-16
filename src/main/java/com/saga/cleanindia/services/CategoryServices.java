package com.saga.cleanindia.services;

import java.util.List;

import com.saga.cleanindia.payloads.CategoryDto;

public interface CategoryServices {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();
}
