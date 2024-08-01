package com.example.job_portal.usermanagement.controller;

import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.usermanagement.dto.request.UserCreationRequest;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * createUser
     */
    @PostMapping("")
    ResponseEntity<GenericResponse<User>> createUser(@RequestBody @Valid UserCreationRequest request){

        return null;
    }




}
