package com.yash.blog.service;

import java.util.List;

import com.yash.blog.utility.UserDto;

public interface UserService {
	
	UserDto registerUser(UserDto userdto);
	
	List<UserDto> getAllUser();
	UserDto getUserById(Integer userId);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	void deleteUser(Integer userId);
	
	

}
