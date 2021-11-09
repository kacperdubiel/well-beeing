package com.wellbeeing.wellbeeing.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoleToUserIdRequest {
    private UUID userId;
    private String role;
}
