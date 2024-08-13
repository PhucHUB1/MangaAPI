package com.example.mangaapi.service;

import com.example.mangaapi.dtos.request.RoleRequest;
import com.example.mangaapi.dtos.response.RoleResponse;
import com.example.mangaapi.mapper.RoleMapper;
import com.example.mangaapi.repository.PermissionRepository;
import com.example.mangaapi.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request){
        var role = roleMapper.toRole(request);

       var permission = permissionRepository.findAllById(request.getPermissions());
       role.setPermissions(new HashSet<>(permission));

       role = roleRepository.save(role);
      return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAllRoles(){
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(int roleId){
        roleRepository.deleteById(roleId);
    }
}
