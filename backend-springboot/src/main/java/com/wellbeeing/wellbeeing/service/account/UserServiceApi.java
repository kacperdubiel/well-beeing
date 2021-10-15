package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.User;

public interface UserServiceApi {
    public boolean register(User user);
    public boolean addRoleToUser(String userUsername, String role);
}
