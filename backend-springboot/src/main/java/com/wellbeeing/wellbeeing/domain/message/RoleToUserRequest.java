package com.wellbeeing.wellbeeing.domain.message;

import java.util.List;

public class RoleToUserRequest {

    private String userUsername;
    private String role;

    public RoleToUserRequest(String userUsername, String role){
        this.userUsername = userUsername;
        this.role = role;
    }

    public String getUsername() {
        return userUsername;
    }

    public String getRole() {
        return role;
    }


}
