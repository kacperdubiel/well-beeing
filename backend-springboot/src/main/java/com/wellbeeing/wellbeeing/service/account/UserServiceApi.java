package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.User;

import java.util.UUID;

public interface UserServiceApi {
    boolean register(User user);
    boolean addRoleToUser(String userUsername, String role);

    UUID findUserIdByUsername(String name);
}
