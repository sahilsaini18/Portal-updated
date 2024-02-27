package com.efacademy.learning.portal.mapper;

import org.mapstruct.Mapper;

import com.efacademy.learning.portal.dto.RegisterUserDto;
import com.efacademy.learning.portal.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends IEntityMapper<RegisterUserDto, User> {

}
