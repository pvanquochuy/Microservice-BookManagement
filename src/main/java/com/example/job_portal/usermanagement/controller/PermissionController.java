package com.example.job_portal.usermanagement.controller;


import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.PermissionDTO;
import com.example.job_portal.usermanagement.request.PermissionRequest;
import com.example.job_portal.usermanagement.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ResponseEntity<GenericResponse<PermissionDTO>> create(@RequestBody @Valid PermissionRequest request){
        PermissionDTO result = permissionService.create(request);
        GenericResponse<PermissionDTO> response = GenericResponse
                .<PermissionDTO>builder()
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
    ResponseEntity<GenericResponse<List<PermissionDTO>>> getAll(){
        List<PermissionDTO> result = permissionService.getAll();
        GenericResponse<List<PermissionDTO>> response = GenericResponse
                .<List<PermissionDTO>>builder()
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
        return GenericResponse
                .builder()
                .message(MessageDTO
                        .builder()
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .messageDetail(MessageConstant.DELETE_DATA_SUCCESS)
                        .build())
                .isSuccess(true)
                .build();
    }
}
