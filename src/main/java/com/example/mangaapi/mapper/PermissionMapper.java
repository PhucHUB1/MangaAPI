package com.example.mangaapi.mapper;

import com.example.mangaapi.dtos.request.PermissionRequest;
import com.example.mangaapi.dtos.response.PermissionResponse;
import com.example.mangaapi.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);


    PermissionResponse permissionToPermissionResponse(Permission permission);

    Permission permissionRequestToPermission(PermissionRequest permissionRequest);

}
