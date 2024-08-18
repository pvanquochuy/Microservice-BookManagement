package com.example.bookmanagement.usermanagement.mapper;

import com.example.bookmanagement.usermanagement.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.bookmanagement.usermanagement.entity.User;
import com.example.bookmanagement.usermanagement.dto.request.UserCreationRequest;
import com.example.bookmanagement.usermanagement.dto.request.UserUpdateRequest;

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
    UserResponse toUserResponse(User entity);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
