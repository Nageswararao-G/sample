package com.nagesh.blog.service;

import java.util.List;

import com.nagesh.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUsers();
	UserDto updateUser(UserDto userDto, Integer id);
	void deleteUser(Integer id);
}
