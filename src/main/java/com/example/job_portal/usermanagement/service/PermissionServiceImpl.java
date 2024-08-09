package com.example.job_portal.usermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.job_portal.usermanagement.dto.PermissionDTO;
import com.example.job_portal.usermanagement.entity.Permission;
import com.example.job_portal.usermanagement.mapper.PermissionMapper;
import com.example.job_portal.usermanagement.repository.PermissionRepository;
import com.example.job_portal.usermanagement.request.PermissionRequest;

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
    public PermissionDTO create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toDto(permission);
    }

    @Override
    public List<PermissionDTO> getAll() {
        var permission = permissionRepository.findAll();
        return permission.stream().map(permissionMapper::toDto).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
