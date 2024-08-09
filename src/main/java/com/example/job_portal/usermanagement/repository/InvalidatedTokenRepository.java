package com.example.job_portal.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.job_portal.usermanagement.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
