package com.example.job_portal.usermanagement.service;

import java.util.List;

import com.example.job_portal.usermanagement.dto.response.PermissionResponse;
import com.example.job_portal.usermanagement.dto.request.PermissionRequest;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
