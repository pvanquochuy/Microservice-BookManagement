package com.example.job_portal.usermanagement.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String password;
    String firstName;
    String lastName;

    LocalDate dob;

    //    @DobConstraint(min = 18, message = "INVALID_DOB")
    List<String> roles;
}
