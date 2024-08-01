package com.example.job_portal.common.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

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
