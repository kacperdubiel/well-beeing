package com.wellbeeing.wellbeeing.service;

import com.wellbeeing.wellbeeing.domain.Role;
import com.wellbeeing.wellbeeing.domain.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public interface UserServiceApi {
    public boolean register(User user);
    public boolean addRoleToUser(String userUsername, String role);
}
