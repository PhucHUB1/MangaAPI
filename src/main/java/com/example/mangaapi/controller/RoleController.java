package com.example.mangaapi.controller;

import com.example.mangaapi.dtos.request.ApiResponse;
import com.example.mangaapi.dtos.request.RoleRequest;
import com.example.mangaapi.dtos.response.RoleResponse;
import com.example.mangaapi.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;


    @PostMapping("/create")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> findAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{roleId}")
    ApiResponse<String> deleteRole(@PathVariable("roleId") int roleId) {
        roleService.deleteRole(roleId);
        return ApiResponse.<String>builder()
                .result("Role has been deleted")
                .build();

    }
}
