package com.example.bookmanagement.usermanagement.service;

import java.text.ParseException;

import com.example.bookmanagement.usermanagement.dto.response.AuthenticationResponse;
import com.example.bookmanagement.usermanagement.dto.response.IntrospectResponse;
import com.example.bookmanagement.usermanagement.dto.request.AuthenticationRequest;
import com.example.bookmanagement.usermanagement.dto.request.IntrospectRequest;
import com.example.bookmanagement.usermanagement.dto.request.LogoutRequest;
import com.example.bookmanagement.usermanagement.dto.request.RefreshRequest;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse outboundAuthentication(String code);
}
