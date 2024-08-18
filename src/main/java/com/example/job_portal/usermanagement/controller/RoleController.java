package com.example.job_portal.usermanagement.controller;


import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.PermissionDTO;
import com.example.job_portal.usermanagement.dto.RoleDTO;
import com.example.job_portal.usermanagement.request.PermissionRequest;
import com.example.job_portal.usermanagement.request.RoleRequest;
import com.example.job_portal.usermanagement.service.PermissionService;
import com.example.job_portal.usermanagement.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ResponseEntity<GenericResponse<RoleDTO>> create(@RequestBody @Valid RoleRequest request){
        RoleDTO result = roleService.create(request);
        GenericResponse<RoleDTO> response = GenericResponse
                .<RoleDTO>builder()
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
    ResponseEntity<GenericResponse<List<RoleDTO>>> getAll(){
        List<RoleDTO> result = roleService.getAll();
        GenericResponse<List<RoleDTO>> response = GenericResponse
                .<List<RoleDTO>>builder()
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
