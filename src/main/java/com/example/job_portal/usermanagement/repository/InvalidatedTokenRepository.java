package com.example.job_portal.usermanagement.repository;

import com.example.job_portal.usermanagement.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
