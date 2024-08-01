package com.example.job_portal.usermanagement.service;

import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.request.UserCreationRequest;

public interface UserService {

    /**
     * create a new user
     *
     * @param request the data transfer object containing user details
     */
    UserDTO create(UserCreationRequest request);


}
