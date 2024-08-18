package com.example.job_portal.usermanagement.request;

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
public class IntrospectRequest {
    String token;

}
