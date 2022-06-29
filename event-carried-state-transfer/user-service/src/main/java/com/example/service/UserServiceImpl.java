package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.dto.UserDto;
import com.example.entity.Users;
import com.example.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private KafkaTemplate<Long, String> kafkaTemplate;

	@Override
	public Long createUser(UserDto userDto) {
		Users user = new Users();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        return this.usersRepository.save(user).getId();
	}

	
	@Transactional
	@Override
	public void updateUser(UserDto userDto) {
		this.usersRepository.findById(userDto.getId())
			.ifPresent(user -> {
				user.setFirstname(userDto.getFirstname());
                user.setLastname(userDto.getLastname());
                user.setEmail(userDto.getEmail());
                this.raiseEvent(userDto);
			});
	}


	private void raiseEvent(UserDto userDto) {
		System.out.println("UserServiceImpl | raiseEvent");
		try {
			String value = OBJECT_MAPPER.writeValueAsString(userDto);
			this.kafkaTemplate.sendDefault(userDto.getId(), value);
		} catch (JsonProcessingException e) {
			log.error("### UserServiceImpl | raiseEvent {}", e.getMessage());
		}
	}
}
