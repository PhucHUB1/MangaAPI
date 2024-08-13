package com.example.mangaapi.mapper;

import com.example.mangaapi.dtos.request.RoleRequest;
import com.example.mangaapi.dtos.response.RoleResponse;
import com.example.mangaapi.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target ="permissions",ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
