package com.nagesh.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.nagesh.blog.entities.Post;

public class CategoryDto {

	private Integer categoryId;

	@NotEmpty
	@Size(min = 3, message = "categoryTitle should be min of 3 characters.")
	private String categoryTitle;

	@NotEmpty(message = "categoryDescription should not be blank")
	private String categoryDescription;

	/*
	 * private List<Post> posts = new ArrayList<>();
	 * 
	 * public List<Post> getPosts() { return posts; }
	 * 
	 * public void setPosts(List<Post> posts) { this.posts = posts; }
	 */

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}
