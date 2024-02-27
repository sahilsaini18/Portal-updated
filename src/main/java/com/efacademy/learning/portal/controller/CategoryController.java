package com.efacademy.learning.portal.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efacademy.learning.portal.dto.CategoryDto;
import com.efacademy.learning.portal.entity.Category;
import com.efacademy.learning.portal.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	private static final String MESSAGE_KEY = "Message";

	@PostMapping("/create")
	public ResponseEntity<HashMap<String, String>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		boolean add = categoryService.addCategory(categoryDto);
		HashMap<String, String> response = new HashMap<>();
		try {
			if (add) {
				response.put(MESSAGE_KEY, "Category Added.");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			} else {
				response.put(MESSAGE_KEY, "Something went wrong");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.put(MESSAGE_KEY, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (InternalError e) {
			response.put(MESSAGE_KEY, "An unexpected error occurred");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllCategory() {
		try {
			List<Category> allCategories = categoryService.getAllCategories();

			if (allCategories != null) {
				HashMap<String, List<Category>> response = new HashMap<>();
				response.put("categories", allCategories);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				HashMap<String, String> noCategoriesResponse = new HashMap<>();
				noCategoriesResponse.put(MESSAGE_KEY, "No categories found.");
				return new ResponseEntity<>(noCategoriesResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			HashMap<String, String> errorResponse = new HashMap<>();
			errorResponse.put(MESSAGE_KEY, e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		} catch (InternalError e) {
			HashMap<String, String> errorResponse = new HashMap<>();
			errorResponse.put(MESSAGE_KEY, "An unexpected error occurred");
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
