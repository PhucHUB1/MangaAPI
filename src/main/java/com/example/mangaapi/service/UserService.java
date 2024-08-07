package com.example.mangaapi.service;

import com.example.mangaapi.dtos.request.UserCreationRequest;
import com.example.mangaapi.dtos.request.UserUpdateRequest;
import com.example.mangaapi.dtos.response.UserResponse;
import com.example.mangaapi.entity.User;
import com.example.mangaapi.exception.AppException;
import com.example.mangaapi.exception.ErrorCode;
import com.example.mangaapi.mapper.UserMapper;
import com.example.mangaapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
          throw new AppException(ErrorCode.USER_EXISTS);
      }
        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
