package com.example.bookmanagement.usermanagement.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // tự động khởi tạo đối tượng
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotBlank
    @Size(min = 6, message = "username must be at least 6 characters")
    private String username;

    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    String firstName;
    String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;
}
