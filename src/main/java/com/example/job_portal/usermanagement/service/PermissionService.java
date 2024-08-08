package com.example.job_portal.usermanagement.service;

import com.example.job_portal.usermanagement.dto.PermissionDTO;
import com.example.job_portal.usermanagement.request.PermissionRequest;

import java.util.List;

public interface PermissionService {
    PermissionDTO create(PermissionRequest request);
    List<PermissionDTO> getAll();
    void delete(String permission);
}
