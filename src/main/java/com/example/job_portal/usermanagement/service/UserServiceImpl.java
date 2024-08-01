package com.example.job_portal.usermanagement.service;

import com.example.job_portal.common.constant.MessageCodeConstant;
import com.example.job_portal.common.exception.UserManagementException;
import com.example.job_portal.usermanagement.dto.UserDTO;
import com.example.job_portal.usermanagement.mapper.UserMapper;
import com.example.job_portal.usermanagement.request.UserCreationRequest;
import com.example.job_portal.usermanagement.entity.User;
import com.example.job_portal.usermanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDTO create(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new UserManagementException(MessageCodeConstant.USER_EXISTED, "Username is existed");
        User user = userMapper.toUser(request);
        User result = userRepository.save(user);

        return userMapper.toDto(result);
    }

}
