package com.example.job_portal.usermanagement.service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.exception.AppException;
import com.example.job_portal.usermanagement.dto.AuthenticationDTO;
import com.example.job_portal.usermanagement.dto.IntrospectDTO;
import com.example.job_portal.usermanagement.entity.InvalidatedToken;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.repository.InvalidatedTokenRepository;
import com.example.job_portal.usermanagement.repository.UserRepository;
import com.example.job_portal.usermanagement.request.AuthenticationRequest;
import com.example.job_portal.usermanagement.request.IntrospectRequest;
import com.example.job_portal.usermanagement.request.LogoutRequest;
import com.example.job_portal.usermanagement.request.RefreshRequest;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;

    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Override
    public IntrospectDTO introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectDTO.builder().valid(isValid).build();
    }

    @Override
    public AuthenticationDTO authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new AppException(MessageCodeConstant.USER_NOT_EXISTED, MessageConstant.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(MessageCodeConstant.UNAUTHORIZED, MessageConstant.INVALID_TOKEN);

        var token = generateToken(user);
        return AuthenticationDTO.builder().token(token).authenticated(true).build();
    }

    private String generateToken(User user) {
        log.info("generate token");
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + VALID_DURATION * 1000);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("phamvanquochuy")
                .issueTime(new Date())
                .expirationTime(expirationTime)
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        log.info("jwtClamset: ", jwtClaimsSet.toJSONObject());
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create token: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception) {
            log.info("Token already expired");
        }
    }

    @Override
    public AuthenticationDTO refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(MessageCodeConstant.UNAUTHORIZED, MessageConstant.UNAUTHORIZED));

        var token = generateToken(user);

        return AuthenticationDTO.builder().token(token).authenticated(true).build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getIssueTime()
                        .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(MessageCodeConstant.UNAUTHORIZED, MessageConstant.UNAUTHORIZED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(MessageCodeConstant.UNAUTHORIZED, MessageConstant.UNAUTHORIZED);

        return signedJWT;
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }

        return stringJoiner.toString();
    }
}
