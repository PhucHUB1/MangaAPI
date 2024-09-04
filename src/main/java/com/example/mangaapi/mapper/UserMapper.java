package com.example.mangaapi.mapper;

import com.example.mangaapi.dtos.request.UserCreationRequest;
import com.example.mangaapi.dtos.request.UserUpdateRequest;
import com.example.mangaapi.dtos.response.UserResponse;
import com.example.mangaapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roleId", target = "id")
    User UserRequestToUser(UserCreationRequest request);

    UserResponse UserToUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}