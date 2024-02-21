package com.efacademy.learning.portal.dto;

import java.util.Set;

import com.efacademy.learning.portal.entity.Courses;
import com.efacademy.learning.portal.entity.Role;

import lombok.Data;

@Data
public class RegisterUserDto {

	private String name;

	private String email;

	private String password;

	private Set<Role> roles;

	private Set<Courses> courses;
}
