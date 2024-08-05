package com.example.job_portal.usermanagement.service;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.constant.MessageConstant;
import com.example.job_portal.common.exception.AppException;
import com.example.job_portal.common.exception.UserManagementException;
import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.mapper.UserMapper;
import com.example.job_portal.usermanagement.request.UserCreationRequest;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.repository.UserRepository;
import com.example.job_portal.usermanagement.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDTO createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new UserManagementException(MessageCodeConstant.USER_EXISTED, MessageConstant.USER_IS_EXISTED);
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User result = userRepository.save(user);

        return userMapper.toDto(result);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
    @Override
    public UserDTO getUser(String id) {
        return userMapper.toDto(
                userRepository.findById(id).orElseThrow(() -> new AppException(MessageCodeConstant.USER_NOT_EXISTED, MessageConstant.USER_NOT_EXISTED)));
    }

    @Override
    public UserDTO updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(MessageCodeConstant.USER_NOT_EXISTED, MessageConstant.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        return userMapper.toDto(userRepository.save(user));
    }
    
    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
