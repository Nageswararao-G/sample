package com.nagesh.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.blog.entities.Category;
import com.nagesh.blog.entities.Post;
import com.nagesh.blog.entities.User;
import com.nagesh.blog.exceptions.ResourceNotFoundException;
import com.nagesh.blog.payloads.PostDto;
import com.nagesh.blog.repository.CategoryRepository;
import com.nagesh.blog.repository.PostRepository;
import com.nagesh.blog.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = postDtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post savedPost = postRepository.save(post);
		return postToPostDto(savedPost);
	}

		public Post postDtoToPost(PostDto postDto) {

		Post post = modelMapper.map(postDto, Post.class);

		// Post Post = new Post();
		// Post.setId(PostDto.getId());
		// Post.setName(PostDto.getName());
		// Post.setEmail(PostDto.getEmail());
		// Post.setPassword(PostDto.getPassword());
		// Post.setAbout(PostDto.getAbout());

		return post;
	}

	public PostDto postToPostDto(Post post) {

		PostDto postDto = modelMapper.map(post, PostDto.class);

		// PostDto PostDto = new PostDto();
		// PostDto.setId(Post.getId());
		// PostDto.setName(Post.getName());
		// PostDto.setEmail(Post.getEmail());
		// PostDto.setPassword(Post.getPassword());
		// PostDto.setAbout(Post.getAbout());

		return postDto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		
		return postToPostDto(post);
	}

	@Override
	public List<PostDto> getAllPosts() {
		
		List<Post> posts = postRepository.findAll();
		List<PostDto> postDtos = posts.stream().map(post->postToPostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto updatePost(PostDto PostDto, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		
		post.setAddedDate(PostDto.getAddedDate());
		post.setImageName(PostDto.getImageName());
		post.setTitle(PostDto.getTitle());
		post.setContent(PostDto.getContent());
		Post updatedPost = postRepository.save(post);

		return postToPostDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		
		postRepository.deleteById(postId);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		
		List<Post> posts = postRepository.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(post->postToPostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		
		List<Post> posts = postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post->postToPostDto(post)).collect(Collectors.toList());
		return postDtos;
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
