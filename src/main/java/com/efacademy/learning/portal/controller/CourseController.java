package com.efacademy.learning.portal.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efacademy.learning.portal.dto.CourseDto;
import com.efacademy.learning.portal.dto.GetCourseDto;
import com.efacademy.learning.portal.entity.CoursesCategory;
import com.efacademy.learning.portal.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	private static final String MESSAGE_KEY = "Message";

	@PostMapping("/create")
	public ResponseEntity<?> createCourses(@Valid @RequestBody CourseDto courseDto) {
		try {
			courseService.addCourse(courseDto);
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Course added successfully"),
					HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (InternalError e) {

			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/view")
	public ResponseEntity<?> viewCourses(@Valid @RequestBody GetCourseDto getCourseDto) {
		try {
			Optional<List<CoursesCategory>> response = courseService.getCourse(getCourseDto);
			if (response.isEmpty()) {
				return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "Category does not exist"),
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (InternalError e) {
			return new ResponseEntity<>(Collections.singletonMap(MESSAGE_KEY, "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/view/{id}")
	public ResponseEntity<?> viewCourseById(@Valid @PathVariable Long id, @RequestBody GetCourseDto getCourseDto) {
		return courseService.getCourseId(id, getCourseDto);
	}
}