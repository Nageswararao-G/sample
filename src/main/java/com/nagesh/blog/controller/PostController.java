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
import com.nagesh.blog.payloads.PostDto;
import com.nagesh.blog.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, 
			                                  @PathVariable Integer userId,
			                                  @PathVariable Integer categoryId ) {
	
		PostDto savedPost = postService.createPost(postDto,userId,categoryId);
		
		return new ResponseEntity<PostDto>(savedPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDto> postDtos = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		
		List<PostDto> postDtos = postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getAllPostById(@PathVariable Integer userId,
            @PathVariable Integer postId ){
		
		PostDto postDto = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		
		PostDto updatedPost = postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	
	 
}
