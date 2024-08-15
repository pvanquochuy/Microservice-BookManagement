package com.example.job_portal.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.request.UserCreationRequest;
import com.example.job_portal.usermanagement.request.UserUpdateRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true) // Nếu không muốn ánh xạ id từ UserCreationRequest
    User toUser(UserCreationRequest request);

    /**
     * Map entity to dto
     *
     * @param entity the entity
     * @return the dto
     */
    @Mapping(target = "id", source = "id")
    UserDTO toDto(User entity);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
