package com.example.job_portal.usermanagement.controller;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.dto.GenericResponse;
import com.example.job_portal.common.dto.MessageDTO;
import com.example.job_portal.usermanagement.dto.response.AuthenticationResponse;
import com.example.job_portal.usermanagement.dto.response.IntrospectResponse;
import com.example.job_portal.usermanagement.dto.request.AuthenticationRequest;
import com.example.job_portal.usermanagement.dto.request.IntrospectRequest;
import com.example.job_portal.usermanagement.dto.request.LogoutRequest;
import com.example.job_portal.usermanagement.dto.request.RefreshRequest;
import com.example.job_portal.usermanagement.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/outbound/authentication")
    ResponseEntity<GenericResponse<AuthenticationResponse>> outboundAuthentication
    (@RequestParam("code") String code){
        var result = authenticationService.outboundAuthentication(code);
        GenericResponse<AuthenticationResponse> response = GenericResponse.<AuthenticationResponse>builder()
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
     *  authenticate API
     *
     * @param request
     * @return GenericResponse<AuthenticationDTO>
     */
    @PostMapping("/token")
    ResponseEntity<GenericResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        GenericResponse<AuthenticationResponse> response = GenericResponse.<AuthenticationResponse>builder()
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
    ResponseEntity<GenericResponse<IntrospectResponse>> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        GenericResponse<IntrospectResponse> response = GenericResponse.<IntrospectResponse>builder()
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
    ResponseEntity<GenericResponse<AuthenticationResponse>> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        GenericResponse<AuthenticationResponse> response = GenericResponse.<AuthenticationResponse>builder()
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
    ResponseEntity<GenericResponse<Void>> authenticate(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        GenericResponse<Void> response = GenericResponse.<Void>builder().build();
        return ResponseEntity.ok(response);
    }
}
