package com.example.bookmanagement.usermanagement.service;

import java.util.List;

import com.example.bookmanagement.usermanagement.dto.response.UserResponse;
import com.example.bookmanagement.usermanagement.dto.request.UserCreationRequest;
import com.example.bookmanagement.usermanagement.dto.request.UserUpdateRequest;

public interface UserService {

    /**
     * create a new user
     *
     * @param request the data transfer object containing user details
     */
    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUser(String id);

    UserResponse getMyInfo();

    void deleteUser(String userId);

    /**
     * update a new user
     *
     * @param request the data transfer object containing user details
     */
    UserResponse updateUser(String userId, UserUpdateRequest request);
}
