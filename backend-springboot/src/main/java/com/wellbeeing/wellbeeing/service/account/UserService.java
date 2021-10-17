package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;

import java.util.UUID;

public interface UserService {
    public boolean register(User user);
    public boolean addRoleToUser(String userUsername, String role);
}
