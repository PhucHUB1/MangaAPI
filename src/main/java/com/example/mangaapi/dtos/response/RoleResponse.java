package com.example.mangaapi.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
      int id;
      String name;
     String description;

     Set<PermissionResponse> permissions;
}
