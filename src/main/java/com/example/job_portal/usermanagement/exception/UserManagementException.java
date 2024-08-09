package com.example.job_portal.usermanagement.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception class for handling User Management errors.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserManagementException extends RuntimeException {
    private String messageCode;

    public UserManagementException(String messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
    }

    public UserManagementException(String messageCode, String message, Throwable cause) {
        super(message, cause);
        this.messageCode = messageCode;
    }
}
