package com.example.job_portal.usermanagement.controller;

import java.util.List;

import com.example.job_portal.usermanagement.dto.response.PermissionResponse;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.request.PermissionRequest;
import com.example.job_portal.usermanagement.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    /**
     * Create user API
     *
     * @param request user request
     * @return GenericResponse<Object>
     */
    @PostMapping
    ResponseEntity<GenericResponse<PermissionResponse>> create(@RequestBody @Valid PermissionRequest request) {
        PermissionResponse result = permissionService.create(request);
        GenericResponse<PermissionResponse> response = GenericResponse.<PermissionResponse>builder()
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
     * Get all permission API
     *
     * @return GenericResponse<Object>
     */
    @GetMapping
    ResponseEntity<GenericResponse<List<PermissionResponse>>> getAll() {
        List<PermissionResponse> result = permissionService.getAll();
        GenericResponse<List<PermissionResponse>> response = GenericResponse.<List<PermissionResponse>>builder()
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
     * Deletes permission.
     *
     * @param permission  Permission of the  to delete.
     * @return ResponseEntity with a message indicating success or failure.
     */
    @DeleteMapping("/{permission}")
    public GenericResponse<Object> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return GenericResponse.builder()
                .message(MessageDTO.builder()
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .messageDetail(MessageConstant.DELETE_DATA_SUCCESS)
                        .build())
                .isSuccess(true)
                .build();
    }
}
