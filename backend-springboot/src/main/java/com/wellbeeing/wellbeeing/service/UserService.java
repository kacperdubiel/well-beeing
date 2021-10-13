package com.wellbeeing.wellbeeing.service;

import com.wellbeeing.wellbeeing.domain.ERole;
import com.wellbeeing.wellbeeing.domain.Role;
import com.wellbeeing.wellbeeing.domain.User;
import com.wellbeeing.wellbeeing.repository.RoleDAO;
import com.wellbeeing.wellbeeing.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService, UserServiceApi {
    private UserDAO userDAO;
    @Qualifier("roleDAO")
    @Autowired
    private RoleDAO roleDAO;
    public UserService(@Qualifier("userDAO") UserDAO userDAO){
        this.userDAO = userDAO;
    }
//    public UserService(@Qualifier("roleDAO") RoleDAO roleDAO){
//        this.roleDAO = roleDAO;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userDAO.findUserByEmail(email).orElse(null);
        if(foundUser != null)
            return foundUser;
        else throw new UsernameNotFoundException("User: " + email + " not found");
    }

    @Override
    public boolean register(User user) {
        if(userDAO.findUserByEmail(user.getUsername()).orElse(null) == null){
            Set<Role> roles = new HashSet<>();
            roles.add(roleDAO.findRoleByName(ERole.ROLE_BASIC_USER).orElse(null));
            user.setRoles(roles);
            System.out.println("User:");
            System.out.println(user);
            userDAO.save(new User(user.getUsername(),
                    BCrypt.hashpw(user.getPassword(), BCrypt.gensalt("$2a$")), user.getRoles()));
            return true;
        }
        return false;
    }

    @Override
    public boolean addRoleToUser(String userUsername, String role) {
        User foundUser = userDAO.findUserByEmail(userUsername).orElse(null);
        Role foundRole = roleDAO.findRoleByName(ERole.valueOf(role)).orElse(null);
        if (foundUser != null && foundRole != null){
            foundUser.addRole(foundRole);
            userDAO.save(foundUser);
            return true;
        }
        return false;
    }


}
