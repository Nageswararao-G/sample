package com.nagesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagesh.blog.entities.Category;
import com.nagesh.blog.entities.Post;
import com.nagesh.blog.entities.User;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
