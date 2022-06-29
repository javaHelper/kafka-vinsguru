package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDto;
import com.example.service.UserService;

@RestController
@RequestMapping("/user-service")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public Long createUser(@RequestBody UserDto userDto) {
		return this.userService.createUser(userDto);
	}

	@PutMapping("/update")
	public void updateUser(@RequestBody UserDto userDto) {
		this.userService.updateUser(userDto);
	}
}
