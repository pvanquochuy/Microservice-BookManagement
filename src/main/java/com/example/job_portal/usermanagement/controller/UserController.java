package com.example.job_portal.usermanagement.controller;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.request.UserCreationRequest;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.service.UserService;
import com.example.job_portal.usermanagement.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Create user API
     *
     * @param request user request
     * @return GenericResponse<Object>
     */
    @PostMapping
    ResponseEntity<GenericResponse<UserDTO>> createUser(@RequestBody @Valid UserCreationRequest request){
        UserDTO result = userService.create(request);
        GenericResponse<UserDTO> response = GenericResponse
                .<UserDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.CREATE_DATA_SUCCESS)
                        .messageCode(MessageCodeConstant.CREATED_DATA_SUCCESSFULLY)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }




}
