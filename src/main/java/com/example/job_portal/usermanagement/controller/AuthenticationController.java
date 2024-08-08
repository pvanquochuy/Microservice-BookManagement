package com.example.job_portal.usermanagement.controller;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.AuthenticationDTO;
import com.example.job_portal.usermanagement.dto.IntrospectDTO;
import com.example.job_portal.usermanagement.request.AuthenticationRequest;
import com.example.job_portal.usermanagement.request.IntrospectRequest;
import com.example.job_portal.usermanagement.request.LogoutRequest;
import com.example.job_portal.usermanagement.request.RefreshRequest;
import com.example.job_portal.usermanagement.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;


    /**
     *  authenticate API
     *
     * @param request
     * @return GenericResponse<AuthenticationDTO>
     */
    @PostMapping("/token")
    ResponseEntity<GenericResponse<AuthenticationDTO>> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        GenericResponse<AuthenticationDTO> response = GenericResponse
                .<AuthenticationDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.SUCCESS)
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     *  introspect API
     *
     * @param request
     * @return GenericResponse<IntrospectDTO>
     */
    @PostMapping("/introspect")
    ResponseEntity<GenericResponse<IntrospectDTO>> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        GenericResponse<IntrospectDTO> response = GenericResponse
                .<IntrospectDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.SUCCESS)
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     *  refresh API
     *
     * @param request
     * @return GenericResponse<AuthenticationDTO>
     */
    @PostMapping("/refresh")
    ResponseEntity<GenericResponse<AuthenticationDTO>> authenticate(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        GenericResponse<AuthenticationDTO> response = GenericResponse
                .<AuthenticationDTO>builder()
                .isSuccess(true)
                .data(result)
                .message(MessageDTO.builder()
                        .messageDetail(MessageConstant.SUCCESS)
                        .messageCode(MessageCodeConstant.SUCCESS)
                        .build())
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     *  logout API
     *
     * 
     * @return GenericResponse<IntrospectDTO>
     */
    @PostMapping("/logout")
    ResponseEntity<GenericResponse<Void>> authenticate(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        GenericResponse<Void> response = GenericResponse
                .<Void>builder()
                .build();
        return ResponseEntity.ok(response);
    }
}
