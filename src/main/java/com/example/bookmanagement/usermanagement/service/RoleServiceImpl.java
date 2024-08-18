package com.example.bookmanagement.usermanagement.service;

import java.util.HashSet;
import java.util.List;

import com.example.bookmanagement.usermanagement.dto.response.RoleResponse;
import org.springframework.stereotype.Service;

import com.example.bookmanagement.usermanagement.mapper.RoleMapper;
import com.example.bookmanagement.usermanagement.repository.PermissionRepository;
import com.example.bookmanagement.usermanagement.repository.RoleRepository;
import com.example.bookmanagement.usermanagement.dto.request.RoleRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
