package com.efacademy.learning.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.efacademy.learning.portal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(@Param("eml") String email);

}
