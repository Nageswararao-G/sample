package com.nagesh.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.blog.payloads.ApiResponse;
import com.nagesh.blog.payloads.CategoryDto;
import com.nagesh.blog.payloads.UserDto;
import com.nagesh.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	
		CategoryDto savedCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
		
		CategoryDto fetchedCategory = categoryService.getCategoryById(id);
		return new ResponseEntity<CategoryDto>(fetchedCategory, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		
		List<CategoryDto> fetchedAllCategories = categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(fetchedAllCategories, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer id) {
		
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
		
		categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true), HttpStatus.OK);
	}
	
}
