package com.efacademy.learning.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efacademy.learning.portal.entity.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

}
