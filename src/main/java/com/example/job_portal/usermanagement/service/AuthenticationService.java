package com.example.job_portal.usermanagement.service;

import java.text.ParseException;

import com.example.job_portal.usermanagement.dto.response.AuthenticationResponse;
import com.example.job_portal.usermanagement.dto.response.IntrospectResponse;
import com.example.job_portal.usermanagement.dto.request.AuthenticationRequest;
import com.example.job_portal.usermanagement.dto.request.IntrospectRequest;
import com.example.job_portal.usermanagement.dto.request.LogoutRequest;
import com.example.job_portal.usermanagement.dto.request.RefreshRequest;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse outboundAuthentication(String code);
}
