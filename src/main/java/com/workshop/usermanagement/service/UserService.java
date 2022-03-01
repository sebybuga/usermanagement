package com.workshop.usermanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.workshop.usermanagement.dto.UserDto;
import com.workshop.usermanagement.entity.UserEntity;
import com.workshop.usermanagement.repo.UserRepository;
import com.workshop.usermanagement.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;

@Service
public class UserService {

	private UserRepository userRepository;

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDto saveUser(UserDto userDto) {

		UserEntity userEntity = mapper.map(userDto, UserEntity.class);

		Integer id = userDto.getId();
		if (id != null && id > 0) { // trying to update
			Optional<UserEntity> existingUser = userRepository.findById(id);
			if (existingUser != null) {
				userEntity.setId(id);
			}
		}
		/* in case of no id provided, a new entity will be added */
		UserEntity savedUser = userRepository.save(userEntity);
		return mapper.map(savedUser, UserDto.class);

	}

	public UserDto getUser(Integer userId) {
		return mapper.map(userRepository.findById(userId).get(), UserDto.class);
	}

	public boolean deleteUser(Integer id) {
		Optional<UserEntity> existingUser = null;
		boolean response = false;

		if (id != null && id > 0) {
			existingUser = userRepository.findById(id);
			System.out.println(existingUser);
			if (existingUser.isPresent()) {
				userRepository.deleteById(id);
				response = true;
			}
		}

		return response;

	}

	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(userEntity -> mapper.map(userEntity, UserDto.class))
				.collect(Collectors.toList());
	}
	
	public List<UserDto> getAllUsersByIds(List<Integer> userIds) {
		return userRepository.findAllById(userIds).stream().map(userEntity -> mapper.map(userEntity, UserDto.class))
				.collect(Collectors.toList());
	}

}