package com.example.job_portal.usermanagement.exception;


public class RestExceptionHandler extends RuntimeException {
    private final String messageCode;

    public RestExceptionHandler(String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
