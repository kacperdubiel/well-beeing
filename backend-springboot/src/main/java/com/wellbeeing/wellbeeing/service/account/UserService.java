package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.User;

import java.util.UUID;

public interface UserService {
    public boolean register(User user);
    public boolean addRoleToUser(String userUsername, String role);
    public boolean addRoleToUser(UUID userId, String role);
    public UUID findUserIdByUsername(String username);
}
