package com.efacademy.learning.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efacademy.learning.portal.dto.LoginUserDto;
import com.efacademy.learning.portal.dto.RegisterUserDto;
import com.efacademy.learning.portal.entity.User;
import com.efacademy.learning.portal.mapper.UserMapper;
import com.efacademy.learning.portal.repository.RoleRepository;
import com.efacademy.learning.portal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleRepository roleRepository;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userMapper = userMapper;
	}

	public boolean registerUser(RegisterUserDto registerUserDto) {
		try {
			User user = userMapper.toDto(registerUserDto);
			userRepository.save(user);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public boolean loginUser(LoginUserDto loginUser) {
		try {
			User user = userRepository.findByEmail(loginUser.getEmail());

			if (user != null && user.getPassword().equals(loginUser.getPassword())) {
				log.info("User logged in successfully: {}", loginUser.getEmail());
				return true;
			} else {
				log.warn("User authentication failed for email: {}", loginUser.getEmail());
				return false;
			}
		} catch (Exception e) {
			log.error("Error occurred during user authentication.", e);
			return false;
		}
	}
}