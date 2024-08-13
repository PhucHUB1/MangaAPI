package com.example.mangaapi.service;

import com.example.mangaapi.dtos.request.UserCreationRequest;
import com.example.mangaapi.dtos.request.UserUpdateRequest;
import com.example.mangaapi.dtos.response.UserResponse;
import com.example.mangaapi.entity.User;
import com.example.mangaapi.enums.Role;
import com.example.mangaapi.exception.AppException;
import com.example.mangaapi.exception.ErrorCode;
import com.example.mangaapi.mapper.UserMapper;
import com.example.mangaapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final  PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
          throw new AppException(ErrorCode.USER_EXISTS);
      }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo(){
       var context = SecurityContextHolder.getContext();
       String name = context.getAuthentication().getName();

       User user = userRepository.findByUsername(name).orElseThrow(
              ()-> new AppException(ErrorCode.USER_NOT_EXISTED));

       return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() ->
                        new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
