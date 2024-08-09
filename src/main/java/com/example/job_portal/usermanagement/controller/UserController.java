package com.example.job_portal.usermanagement.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.request.UserCreationRequest;
import com.example.job_portal.usermanagement.request.UserUpdateRequest;
import com.example.job_portal.usermanagement.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    /**
     * Create user API
     *
     * @param request user request
     * @return GenericResponse<Object>
     */
    @PostMapping
    ResponseEntity<GenericResponse<UserDTO>> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserDTO result = userService.createUser(request);
        GenericResponse<UserDTO> response = GenericResponse.<UserDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.CREATE_DATA_SUCCESS)
                        .messageCode(MessageCodeConstant.CREATED_DATA_SUCCESSFULLY)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Get all users API
     *
     * @return GenericResponse<Object>
     */
    @GetMapping
    ResponseEntity<GenericResponse<List<UserDTO>>> getUsers() {
        log.info("getUsers:");
        List<UserDTO> result = userService.getUsers();
        GenericResponse<List<UserDTO>> response = GenericResponse.<List<UserDTO>>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.SUCCESS)
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Get  user by id API
     *
     * @return GenericResponse<Object>
     */
    @GetMapping("/{userId}")
    ResponseEntity<GenericResponse<UserDTO>> getUser(@PathVariable String userId) {
        UserDTO result = userService.getUser(userId);
        GenericResponse<UserDTO> response = GenericResponse.<UserDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.SUCCESS)
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-info")
    ResponseEntity<GenericResponse<UserDTO>> getMyInfo() {
        UserDTO result = userService.getMyInfo();
        GenericResponse<UserDTO> response = GenericResponse.<UserDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.SUCCESS)
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * update user by id API
     *
     * @return GenericResponse<Object>
     */
    @PutMapping("/{userId}")
    ResponseEntity<GenericResponse<UserDTO>> updateUser(
            @PathVariable String userId, @RequestBody UserUpdateRequest request) {
        UserDTO user = userService.updateUser(userId, request);
        GenericResponse<UserDTO> response = GenericResponse.<UserDTO>builder()
                .isSuccess(true)
                .data(user)
                .message(MessageDTO.builder()
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .messageDetail("USER_UPDATED")
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Deletes User by ID.
     *
     * @param id ID of the User to delete.
     * @return ResponseEntity with a message indicating success or failure.
     */
    @DeleteMapping("/{id}")
    public GenericResponse<Object> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return GenericResponse.builder()
                .message(MessageDTO.builder()
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .messageDetail(MessageConstant.DELETE_DATA_SUCCESS)
                        .build())
                .isSuccess(true)
                .build();
    }
}
