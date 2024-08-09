package com.example.job_portal.usermanagement.service;

import java.util.List;

import com.example.job_portal.usermanagement.dto.PermissionDTO;
import com.example.job_portal.usermanagement.request.PermissionRequest;

public interface PermissionService {
    PermissionDTO create(PermissionRequest request);

    List<PermissionDTO> getAll();

    void delete(String permission);
}
