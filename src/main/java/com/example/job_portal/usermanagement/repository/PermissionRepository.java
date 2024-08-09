package com.example.job_portal.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_portal.usermanagement.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
