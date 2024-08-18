package com.example.bookmanagement.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmanagement.usermanagement.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
