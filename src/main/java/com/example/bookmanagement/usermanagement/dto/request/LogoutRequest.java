package com.example.bookmanagement.usermanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * verify token request class
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogoutRequest {
    String token;
}
