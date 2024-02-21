package com.efacademy.learning.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efacademy.learning.portal.dto.LoginUserDto;
import com.efacademy.learning.portal.dto.RegisterUserDto;
import com.efacademy.learning.portal.entity.Role;
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
	private RoleRepository roleRepository;

	@Autowired
	private UserMapper userMapper;

	public boolean registerUser(RegisterUserDto registerUser) {

		User user = userRepository.findByEmail(registerUser.getEmail());
		if (user != null)
			return false;

		user = userMapper.toDto(registerUser);
		Role defaultRole = roleRepository.findByRoleType("Learner");

		if (defaultRole == null) {
			defaultRole = new Role();
			defaultRole.setRoleType("Learner");

			roleRepository.save(defaultRole);
		}
		user.getRoles().add(defaultRole);
		userRepository.save(user);

		return true;

	}

	public boolean loginUser(LoginUserDto loginUser) {

		try {
			User user = userRepository.findByEmail(loginUser.getEmail());
			RegisterUserDto userDetails = userMapper.toEntity(user);

			if (userDetails.getPassword().equals(loginUser.getPassword())) {
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