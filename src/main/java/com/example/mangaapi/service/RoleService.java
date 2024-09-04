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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request){
        var role = roleMapper.INSTANCE.RoleRequestToRole(request);

        var permissions = permissionRepository.findAllById(Collections.singleton(request.getPermissionId()));
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.INSTANCE.roleToRoleResponse(role);
    }

    public Set<RoleResponse> getAllRoles() {
        var roles = new HashSet<>(roleRepository.findAll());
        return roleMapper.INSTANCE.rolesToRoleResponses(roles);
    }

    public void deleteRole(int roleId){
        roleRepository.deleteById(roleId);
    }
}
