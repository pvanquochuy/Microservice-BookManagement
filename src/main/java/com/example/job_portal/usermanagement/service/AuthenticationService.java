package com.example.job_portal.usermanagement.service;

import com.example.job_portal.usermanagement.dto.AuthenticationDTO;
import com.example.job_portal.usermanagement.dto.IntrospectDTO;
import com.example.job_portal.usermanagement.request.AuthenticationRequest;
import com.example.job_portal.usermanagement.request.IntrospectRequest;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationDTO authenticate(AuthenticationRequest request);
    IntrospectDTO introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
