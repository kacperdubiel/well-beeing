package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.repository.account.RoleDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
