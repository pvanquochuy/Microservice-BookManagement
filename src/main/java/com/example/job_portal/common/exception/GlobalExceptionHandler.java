package com.example.job_portal.common.exception;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.exception.RestExceptionHandler;
import com.example.job_portal.usermanagement.exception.UserManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<GenericResponse<Object>> handleUserManagementException(UserManagementException ex, WebRequest request) {
        GenericResponse<Object> response = GenericResponse.builder()
                .isSuccess(false)
                .message(MessageDTO.builder()
                        .messageDetail(ex.getMessage())
                        .messageCode(ex.getMessageCode())
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        GenericResponse<Object> response = GenericResponse.builder()
                .isSuccess(false)
                .message(MessageDTO.builder()
                        .messageDetail("Validation failed")
                        .messageCode("VALIDATION_ERROR")
                        .build())
                .data(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<GenericResponse<Object>> handleAppException(AppException ex) {
        GenericResponse<Object> response = GenericResponse.<Object>builder()
                .isSuccess(false)
                .data(null) // No data to return in case of exception
                .message(MessageDTO.builder()
                        .messageDetail(ex.getMessage())
                        .messageCode(ex.getMessageCode())
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Or another appropriate status
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Object>> handleGeneralException(Exception ex) {
        GenericResponse<Object> response = GenericResponse.<Object>builder()
                .isSuccess(false)
                .data(null) // No data to return in case of exception
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.UNAUTHORIZED)
                        .messageCode(MessageCodeConstant.UNCATEGORIED)
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GenericResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        GenericResponse<Object> response = GenericResponse.<Object>builder()
                .isSuccess(false)
                .data(null) // No data to return in case of exception
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.UNAUTHORIZED)
                        .messageCode(MessageCodeConstant.UNAUTHORIZED)
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(RestExceptionHandler.class)
    public ResponseEntity<GenericResponse<Object>> handleRestExceptionHandler(RestExceptionHandler ex) {
        GenericResponse<Object> response = GenericResponse.<Object>builder()
                .isSuccess(false)
                .data(null) // No data to return in case of exception
                .message(MessageDTO.builder()
                        .messageDetail(ex.getMessage())
                        .messageCode(ex.getMessageCode())
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
