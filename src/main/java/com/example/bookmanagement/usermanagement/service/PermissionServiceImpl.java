package com.example.bookmanagement.usermanagement.service;

import java.util.List;

import com.example.bookmanagement.usermanagement.dto.response.PermissionResponse;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.usermanagement.entity.Permission;
import com.example.bookmanagement.usermanagement.mapper.PermissionMapper;
import com.example.bookmanagement.usermanagement.repository.PermissionRepository;
import com.example.bookmanagement.usermanagement.dto.request.PermissionRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
