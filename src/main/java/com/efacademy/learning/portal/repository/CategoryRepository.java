package com.efacademy.learning.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efacademy.learning.portal.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT * FROM course_category WHERE category_type =:categoryType", nativeQuery = true)
	Optional<Category> findByCategoryType(@Param("categoryType") String categoryType);
}
