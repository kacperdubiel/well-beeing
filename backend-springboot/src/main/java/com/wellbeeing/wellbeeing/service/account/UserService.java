package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

public interface UserService {
    public boolean register(User user);
    public boolean addRoleToUser(String userUsername, String role);
    public boolean addRoleToUser(UUID userId, String role);
    public UUID findUserIdByUsername(String username);
    User loadUserByEmail(String email) throws UsernameNotFoundException;
    void changeUserPassword(final User user, final String password);
    void changeUserEmail(UUID userId, String email) throws ConflictException;
    boolean checkIfValidOldPassword(final User user, final String oldPassword);
}
