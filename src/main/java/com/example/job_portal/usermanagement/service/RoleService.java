package com.example.job_portal.usermanagement.service;

import java.util.List;

import com.example.job_portal.usermanagement.dto.RoleDTO;
import com.example.job_portal.usermanagement.request.RoleRequest;

public interface RoleService {

    RoleDTO create(RoleRequest request);

    List<RoleDTO> getAll();

    void delete(String role);
}
