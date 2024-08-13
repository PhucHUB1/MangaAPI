package com.example.mangaapi.controller;

import com.example.mangaapi.dtos.request.ApiResponse;
import com.example.mangaapi.dtos.request.PermissionRequest;
import com.example.mangaapi.dtos.response.PermissionResponse;
import com.example.mangaapi.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping("/create")
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> findAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<String> deletePermission(@PathVariable("permissionId") int permissionId) {
        permissionService.delete(permissionId);
        return ApiResponse.<String>builder()
                .result("Permission has been deleted")
                .build();

    }

}
