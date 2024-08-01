package com.example.job_portal.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DateFormatException extends RuntimeException {

    private String messageCode;

    public DateFormatException(String messageCode, String message, Throwable cause) {
        super(message, cause);
        this.messageCode = messageCode;
    }
}
