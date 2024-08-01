package com.example.job_portal.usermanagement.service;

import com.example.job_portal.usermanagement.dto.request.UserCreationRequest;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request){

        return  null;
    }
}
