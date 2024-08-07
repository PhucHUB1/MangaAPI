package com.example.mangaapi.controller;

import com.example.mangaapi.dtos.request.ApiResponse;
import com.example.mangaapi.dtos.request.UserCreationRequest;
import com.example.mangaapi.dtos.request.UserUpdateRequest;
import com.example.mangaapi.dtos.response.UserResponse;
import com.example.mangaapi.entity.User;
import com.example.mangaapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    ApiResponse<User> createUser(@RequestBody UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping("/all-users")
    List<User> getAllUsers() {
       return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}






































































































































































































































