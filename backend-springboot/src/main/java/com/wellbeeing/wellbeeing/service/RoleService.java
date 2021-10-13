package com.wellbeeing.wellbeeing.service;

import com.wellbeeing.wellbeeing.domain.ERole;
import com.wellbeeing.wellbeeing.domain.Role;
import com.wellbeeing.wellbeeing.domain.User;
import com.wellbeeing.wellbeeing.repository.RoleDAO;
import com.wellbeeing.wellbeeing.repository.UserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("roleService")
public class RoleService implements RoleServiceApi {

    private RoleDAO roleDAO;
    public RoleService(@Qualifier("roleDAO") RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }
    @Override
    public boolean addRole(Role role) {
        if(roleDAO.findRoleByName(role.getRole()).orElse(null) == null){
            roleDAO.save(new Role(role.getRole()));
            return true;
        }
        return false;
    }
}
