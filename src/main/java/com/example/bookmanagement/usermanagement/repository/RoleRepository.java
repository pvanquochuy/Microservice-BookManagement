package com.example.bookmanagement.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmanagement.usermanagement.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
