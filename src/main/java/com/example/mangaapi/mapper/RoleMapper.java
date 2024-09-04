package com.example.mangaapi.mapper;

import com.example.mangaapi.dtos.request.RoleRequest;
import com.example.mangaapi.dtos.response.RoleResponse;
import com.example.mangaapi.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);


    Role RoleRequestToRole(RoleRequest roleRequest);

    RoleResponse roleToRoleResponse(Role role);

    Set<RoleResponse> rolesToRoleResponses(Set<Role> roles);


}
