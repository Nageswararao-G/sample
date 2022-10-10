package com.nagesh.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.blog.entities.Category;
import com.nagesh.blog.exceptions.ResourceNotFoundException;
import com.nagesh.blog.payloads.CategoryDto;
import com.nagesh.blog.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = categoryDtoToCategory(categoryDto);
		Category savedCategory = categoryRepository.save(category);
		return categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		
		return categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtos = categories
				                    .stream()
				                    .map(category -> categoryToCategoryDto(category))
				                    .collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
	 
		
		Category updatedCategory = categoryRepository.save(category);
		
		return categoryToCategoryDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
		
		categoryRepository.delete(category);

	}

	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		
		Category category = modelMapper.map(categoryDto, Category.class);
		
		//User user = new User();
		//user.setId(userDto.getId());
		//user.setName(userDto.getName());
		//user.setEmail(userDto.getEmail());
		//user.setPassword(userDto.getPassword());
		//user.setAbout(userDto.getAbout());
		
		return category;
	}
	
	public CategoryDto categoryToCategoryDto(Category category) {
		
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		
		//UserDto userDto = new UserDto();
		//userDto.setId(user.getId());
		//userDto.setName(user.getName());
		//userDto.setEmail(user.getEmail());
		//userDto.setPassword(user.getPassword());
		//userDto.setAbout(user.getAbout());
		
		return categoryDto;
	}
	
	
}
