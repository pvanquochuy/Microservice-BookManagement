package com.example.job_portal.usermanagement.dto;


import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {
    String name;
    String description;
    Set<PermissionDTO> permissions;
}
