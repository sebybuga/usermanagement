package com.workshop.usermanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.workshop.usermanagement.dto.UserDto;
import com.workshop.usermanagement.entity.UserEntity;
import com.workshop.usermanagement.repo.UserRepository;
import com.workshop.usermanagement.service.UserService;

import org.springframework.stereotype.Service;


@Service
public class UserService{

	private UserRepository userRepository;

    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);

        return mapper.map(savedUser, UserDto.class);
    }

    public UserDto getUser(Integer userId) {
        return mapper.map(userRepository.findById(userId).get(), UserDto.class);
    }
}