package com.example.job_portal.usermanagement.service;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.exception.AppException;
import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.entity.Role;
import com.example.job_portal.usermanagement.exception.UserManagementException;
import com.example.job_portal.usermanagement.mapper.UserMapper;
import com.example.job_portal.usermanagement.repository.RoleRepository;
import com.example.job_portal.usermanagement.request.UserCreationRequest;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.repository.UserRepository;
import com.example.job_portal.usermanagement.request.UserUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;



    @Override
    public UserDTO createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new UserManagementException(MessageCodeConstant.USER_EXISTED, MessageConstant.USER_IS_EXISTED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();

        User result = userRepository.save(user);

        return userMapper.toDto(result);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserDTO> getUsers() {
        log.info("In method get users");
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserDTO getUser(String id) {
        log.info("In method get user by id");
        return userMapper.toDto(
                userRepository.findById(id).orElseThrow(
                        () -> new AppException(MessageCodeConstant.USER_NOT_EXISTED, MessageConstant.USER_NOT_EXISTED)));
    }

    @Override
    public UserDTO getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(MessageCodeConstant.USER_NOT_EXISTED, MessageConstant.USER_NOT_EXISTED));

        return userMapper.toDto(user);
    }

    @Override
    public UserDTO updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(MessageCodeConstant.USER_NOT_EXISTED, MessageConstant.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());


        return userMapper.toDto(userRepository.save(user));
    }
    
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
