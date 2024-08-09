package com.example.job_portal.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.job_portal.usermanagement.dto.RoleDTO;
import com.example.job_portal.usermanagement.entity.Role;
import com.example.job_portal.usermanagement.request.RoleRequest;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    /**
     * Map to Role
     *
     * @param request the entity
     * @return the Role
     */
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    /**
     * Map Role to dto
     *
     * @param role the entity
     * @return the dto
     */
    RoleDTO toDto(Role role);
}
