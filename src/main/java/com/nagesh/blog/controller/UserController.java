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
import com.nagesh.blog.payloads.UserDto;
import com.nagesh.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
	
		UserDto savedUser = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
		
		UserDto fetchedUser = userService.getUserById(id);
		return new ResponseEntity<UserDto>(fetchedUser, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		
		List<UserDto> fetchedAllUsers = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(fetchedAllUsers, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer id) {
		
		UserDto updatedUser = userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
		
	 userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
	}
	
}
