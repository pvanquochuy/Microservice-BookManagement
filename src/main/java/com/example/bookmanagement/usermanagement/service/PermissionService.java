package com.example.bookmanagement.usermanagement.service;

import java.util.List;

import com.example.bookmanagement.usermanagement.dto.response.PermissionResponse;
import com.example.bookmanagement.usermanagement.dto.request.PermissionRequest;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
