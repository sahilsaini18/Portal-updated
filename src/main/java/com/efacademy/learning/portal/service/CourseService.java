package com.efacademy.learning.portal.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.efacademy.learning.portal.dto.CourseDto;
import com.efacademy.learning.portal.dto.GetCourseDto;
import com.efacademy.learning.portal.entity.Category;
import com.efacademy.learning.portal.entity.Courses;
import com.efacademy.learning.portal.entity.CoursesCategory;
import com.efacademy.learning.portal.mapper.CourseMapper;
import com.efacademy.learning.portal.repository.CategoryRepository;
import com.efacademy.learning.portal.repository.CourseRepository;
import com.efacademy.learning.portal.repository.CoursesCategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CoursesCategoryRepository coursesCategoryRepository;

	@Autowired
	private CourseMapper courseMapper;

	public boolean addCourse(CourseDto courseDto) {

		Optional<Category> categoryObj = categoryRepository.findByCategoryType(courseDto.getCategory());

		if (categoryObj.isEmpty()) {
			return false;
		}

		Category category = categoryObj.get();
		Courses course = courseMapper.toDto(courseDto);

		CoursesCategory obj = new CoursesCategory();
		obj.setCategory(category);
		obj.setCourse(courseRepository.save(course));
		coursesCategoryRepository.save(obj);

		log.info("Course added successfully: {}", course);
		return true;
	}

	public Optional<List<CoursesCategory>> getCourse(GetCourseDto getCourseDto) {
		Optional<Category> categoryObject = categoryRepository.findByCategoryType(getCourseDto.getCategory());

		if (categoryObject.isEmpty()) {
			return Optional.empty();
		}

		Category category = categoryObject.get();

		List<CoursesCategory> courses = coursesCategoryRepository.findByCategory(category);
		log.info("Retrieved {} courses for category: {}", courses.size(), category.getCategoryType());
		return Optional.of(courses);
	}

	public ResponseEntity<?> getCourseId(Long id, GetCourseDto getCourseDto) {
		List<CoursesCategory> courses = getCourse(getCourseDto).orElse(Collections.emptyList());

		for (CoursesCategory course : courses) {
			if (course.getId().equals(id)) {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(Collections.singletonMap("Message", "Course id doesn't exist"),
				HttpStatus.BAD_REQUEST);
	}
}