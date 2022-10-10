package com.nagesh.blog.service;

import java.util.List;

import com.nagesh.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	  PostDto getPostById(Integer postId); 
	  List<PostDto> getAllPosts(); 
	  PostDto updatePost(PostDto PostDto, Integer postId); 
	  void deletePost(Integer postId);
	  
	  List<PostDto> getPostsByCategory(Integer categoryId);
	  
	  List<PostDto> getPostsByUser(Integer userId);
	  
	  List<PostDto> searchPosts(String keyword);
	 
}
