package com.efacademy.learning.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efacademy.learning.portal.dto.CategoryDto;
import com.efacademy.learning.portal.entity.Category;
import com.efacademy.learning.portal.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public boolean addCategory(CategoryDto categoryDto) {

		try {
			Category toAdd = new Category();
			toAdd.setCategoryType(categoryDto.getCategoryType());

			categoryRepository.save(toAdd);
			log.info("Category added successfully: {}", toAdd);
			return true;
		} catch (Exception e) {
			log.error("Failed to add category.", e);
			return false;
		}
	}

	public Map<String, List<Category>> getCategory() {
		Map<String, List<Category>> response = new HashMap<>();
		response.put("Message", categoryRepository.findAll());
		return response;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
