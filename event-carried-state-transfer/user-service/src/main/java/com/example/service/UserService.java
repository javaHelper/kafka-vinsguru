package com.example.service;

import com.example.dto.UserDto;

public interface UserService {
	Long createUser(UserDto userDto);

	void updateUser(UserDto userDto);
}