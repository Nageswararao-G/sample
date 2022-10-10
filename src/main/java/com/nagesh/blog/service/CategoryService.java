package com.nagesh.blog.service;

import java.util.List;

import com.nagesh.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto getCategoryById(Integer id);
	List<CategoryDto> getAllCategories();
	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	void deleteCategory(Integer id);
}
