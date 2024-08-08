package com.example.job_portal.usermanagement.sercurity;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        MessageDTO messageDTO = MessageDTO.builder().messageCode(MessageCodeConstant.UNAUTHORIZED).build();

        response.setStatus(Integer.parseInt(messageDTO.getMessageCode()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        GenericResponse<Object> entryPointResponse = GenericResponse.<Object>builder()
                .isSuccess(true)
                .data(null) // No data to return in case of exception
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.UNAUTHORIZED)
                        .messageCode(MessageCodeConstant.UNAUTHORIZED)
                        .build())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(entryPointResponse));
        response.flushBuffer();
    }
}
