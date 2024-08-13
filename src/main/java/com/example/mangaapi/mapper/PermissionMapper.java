package com.example.mangaapi.mapper;

import com.example.mangaapi.dtos.request.PermissionRequest;
import com.example.mangaapi.dtos.response.PermissionResponse;
import com.example.mangaapi.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

}
