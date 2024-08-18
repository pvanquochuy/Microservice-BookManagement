package com.example.bookmanagement.usermanagement.service;

import java.util.List;

import com.example.bookmanagement.usermanagement.dto.response.RoleResponse;
import com.example.bookmanagement.usermanagement.dto.request.RoleRequest;

public interface RoleService {

    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String role);
}
