package com.example.job_portal.usermanagement.controller;

import java.util.List;

import com.example.job_portal.usermanagement.dto.response.RoleResponse;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.request.RoleRequest;
import com.example.job_portal.usermanagement.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    /**
     * Create role API
     *
     * @param request user request
     * @return GenericResponse<Object>
     */
    @PostMapping
    ResponseEntity<GenericResponse<RoleResponse>> create(@RequestBody @Valid RoleRequest request) {
        RoleResponse result = roleService.create(request);
        GenericResponse<RoleResponse> response = GenericResponse.<RoleResponse>builder()
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
     * Get all role API
     *
     * @return GenericResponse<Object>
     */
    @GetMapping
    ResponseEntity<GenericResponse<List<RoleResponse>>> getAll() {
        List<RoleResponse> result = roleService.getAll();
        GenericResponse<List<RoleResponse>> response = GenericResponse.<List<RoleResponse>>builder()
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
     * @param role  Permission of the  to delete.
     * @return ResponseEntity with a message indicating success or failure.
     */
    @DeleteMapping("/{role}")
    public GenericResponse<Object> deletePermission(@PathVariable String role) {
        roleService.delete(role);
        return GenericResponse.builder()
                .message(MessageDTO.builder()
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .messageDetail(MessageConstant.DELETE_DATA_SUCCESS)
                        .build())
                .isSuccess(true)
                .build();
    }
}
