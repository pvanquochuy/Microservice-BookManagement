package com.example.job_portal.usermanagement.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<RoleDTO> roles;
}
