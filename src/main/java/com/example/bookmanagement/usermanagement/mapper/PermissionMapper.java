package com.example.bookmanagement.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.example.bookmanagement.usermanagement.dto.response.PermissionResponse;
import com.example.bookmanagement.usermanagement.entity.Permission;
import com.example.bookmanagement.usermanagement.dto.request.PermissionRequest;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    /**
     * Map to permisstion
     *
     * @param request the entity
     * @return the permisstion
     */
    Permission toPermission(PermissionRequest request);

    /**
     * Map permisstion to dto
     *
     * @param permission the entity
     * @return the dto
     */
    PermissionResponse toPermissionResponse(Permission permission);
}
