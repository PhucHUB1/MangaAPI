package com.example.mangaapi.service;

import com.example.mangaapi.dtos.request.PermissionRequest;
import com.example.mangaapi.dtos.response.PermissionResponse;
import com.example.mangaapi.mapper.PermissionMapper;
import com.example.mangaapi.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

   public PermissionResponse create(PermissionRequest request){
       var permission = permissionMapper.INSTANCE.permissionRequestToPermission(request);
       permission = permissionRepository.save(permission);
       return permissionMapper.INSTANCE.permissionToPermissionResponse(permission);
   }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::permissionToPermissionResponse).toList();
    }

    public void delete(int permissionId){
        permissionRepository.deleteById(permissionId);
    }
}
