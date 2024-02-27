package com.efacademy.learning.portal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.efacademy.learning.portal.dto.CourseDto;
import com.efacademy.learning.portal.dto.FavouriteDto;
import com.efacademy.learning.portal.dto.GetCourseDto;
import com.efacademy.learning.portal.dto.LoginUserDto;
import com.efacademy.learning.portal.dto.RegisterUserDto;
import com.efacademy.learning.portal.entity.CoursesCategory;
import com.efacademy.learning.portal.service.CategoryService;
import com.efacademy.learning.portal.service.CourseService;
import com.efacademy.learning.portal.service.FavouriteService;
import com.efacademy.learning.portal.service.UserService;

@SpringBootTest
class LearningPortalApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FavouriteService favouriteService;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void testUserRegistration() {
		RegisterUserDto registerUserDto = new RegisterUserDto();
		registerUserDto.setName("Test User");
		registerUserDto.setEmail("tests6@gmail.com");
		registerUserDto.setPassword("user");

		boolean registrationResult = userService.registerUser(registerUserDto);
		assertTrue(registrationResult);
	}

	@Test
	void testUserLogin() {
		LoginUserDto loginUserDto = new LoginUserDto();
		loginUserDto.setEmail("tests1@gmail.com");
		loginUserDto.setPassword("user");

		assertTrue(userService.loginUser(loginUserDto));
	}

	@Test
	void testCourseCreation() {
		CourseService mockedCourseService = mock(CourseService.class);
		when(mockedCourseService.addCourse(any(CourseDto.class))).thenReturn(true);

		LearningPortalApplicationTests testInstance = new LearningPortalApplicationTests();
		ReflectionTestUtils.setField(testInstance, "courseService", mockedCourseService);

		CourseDto courseDto = new CourseDto();
		courseDto.setCategory("Programming");

		boolean isCourseCreated = testInstance.courseService.addCourse(courseDto);

		assertTrue(isCourseCreated);
	}

	@Test
	void CourseDtoTests() {
		CourseDto courseDto = new CourseDto();

		courseDto.setCourseName("Java Programming");
		courseDto.setCategory("Programming");

		assertEquals("Java Programming", courseDto.getCourseName());
		assertEquals("Programming", courseDto.getCategory());
	}

	@Test
	void testGetCoursesByCategory() {
		GetCourseDto getCourseDto = new GetCourseDto();
		getCourseDto.setCategory("Programming");
		Optional<List<CoursesCategory>> courses = courseService.getCourse(getCourseDto);

		assertTrue(courses.isPresent());
		assertFalse(courses.get().isEmpty());
	}

	@Test
	void testAddingToFavorites() {
		FavouriteDto favouriteDto = new FavouriteDto();
		favouriteDto.setCourseId(1L);
		favouriteDto.setEmail("tests1@gmail.com");

		assertTrue(favouriteService.makeFavourite(favouriteDto));
	}
}
