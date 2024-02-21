package com.efacademy.learning.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efacademy.learning.portal.entity.Category;
import com.efacademy.learning.portal.entity.CoursesCategory;

@Repository
public interface CoursesCategoryRepository extends JpaRepository<CoursesCategory, Long> {

	List<CoursesCategory> findByCategory(Category category);
}