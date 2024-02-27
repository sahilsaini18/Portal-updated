package com.efacademy.learning.portal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.efacademy.learning.portal.dto.CourseDto;
import com.efacademy.learning.portal.entity.Category;
import com.efacademy.learning.portal.entity.Courses;

@Mapper(componentModel = "spring")
public interface CourseMapper extends IEntityMapper<CourseDto, Courses> {

	@Mapping(source = "authorID", target = "authorID")
	Courses toDto(CourseDto dto);

}
