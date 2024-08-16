package com.example.job_portal.usermanagement.service;

import java.util.List;

import com.example.job_portal.usermanagement.dto.response.RoleResponse;
import com.example.job_portal.usermanagement.dto.request.RoleRequest;

public interface RoleService {

    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String role);
}
