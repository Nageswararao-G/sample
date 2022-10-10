package com.nagesh.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.blog.entities.User;
import com.nagesh.blog.exceptions.ResourceNotFoundException;
import com.nagesh.blog.payloads.UserDto;
import com.nagesh.blog.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userDtoToUser(userDto);
		User savedUser = userRepository.save(user);
		return userToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		return userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = users
				                    .stream()
				                    .map(user -> userToUserDto(user))
				                    .collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepository.save(user);
		
		return userToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		userRepository.delete(user);

	}

	public User userDtoToUser(UserDto userDto) {
		
		User user = modelMapper.map(userDto, User.class);
		
		//User user = new User();
		//user.setId(userDto.getId());
		//user.setName(userDto.getName());
		//user.setEmail(userDto.getEmail());
		//user.setPassword(userDto.getPassword());
		//user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto userToUserDto(User user) {
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		//UserDto userDto = new UserDto();
		//userDto.setId(user.getId());
		//userDto.setName(user.getName());
		//userDto.setEmail(user.getEmail());
		//userDto.setPassword(user.getPassword());
		//userDto.setAbout(user.getAbout());
		
		return userDto;
	}
	
	
}
