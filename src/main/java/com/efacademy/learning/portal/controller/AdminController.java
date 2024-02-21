package com.efacademy.learning.portal.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efacademy.learning.portal.dto.LoginUserDto;
import com.efacademy.learning.portal.dto.RegisterUserDto;
import com.efacademy.learning.portal.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	private static final String MESSAGE_KEY = "Message";

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
		HashMap<String, String> response = new HashMap<>();
		try {
			userService.registerUser(registerUserDto);
			response.put(MESSAGE_KEY, "User registered successfully.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put(MESSAGE_KEY, "User already exists");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto) {
		HashMap<String, String> response = new HashMap<>();
		try {
			userService.loginUser(loginUserDto);
			response.put(MESSAGE_KEY, "User logged in successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put(MESSAGE_KEY, "User authentication failed");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
