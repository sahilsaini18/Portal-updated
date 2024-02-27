package com.efacademy.learning.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efacademy.learning.portal.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleType(String roletype);

	Role save(Role role);
}
