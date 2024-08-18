package com.example.job_portal.usermanagement.service;

import com.example.job_portal.usermanagement.dto.RoleDTO;
import com.example.job_portal.usermanagement.mapper.RoleMapper;
import com.example.job_portal.usermanagement.repository.PermissionRepository;
import com.example.job_portal.usermanagement.repository.RoleRepository;
import com.example.job_portal.usermanagement.request.RoleRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService{
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleDTO create(RoleRequest request){
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleDTO> getAll(){
        return roleRepository.findAll()
                .stream().map(roleMapper::toDto)
                .toList();
    }

    @Override
    public void delete (String role){
        roleRepository.deleteById(role);
    }
}
