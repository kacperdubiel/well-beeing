package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.*;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.RoleDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService, UserServiceApi {
    private UserDAO userDAO;
    private ProfileDAO profileDAO;
    private ProfileCardDAO profileCardDAO;
    private RoleDAO roleDAO;

    @Autowired
    public UserService(@Qualifier("userDAO") UserDAO userDAO,
                       @Qualifier("roleDAO") RoleDAO roleDAO,
                       @Qualifier("profileDAO") ProfileDAO profileDAO,
                       @Qualifier("profileCardDAO") ProfileCardDAO profileCardDAO){
        this.userDAO = userDAO;
        this.profileDAO = profileDAO;
        this.profileCardDAO = profileCardDAO;
        this.roleDAO = roleDAO;

    }

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
            user.setRoles(roles);;
            User newUser = new User(user.getUsername(),
                    BCrypt.hashpw(user.getPassword(), BCrypt.gensalt("$2a$")), user.getRoles());
            userDAO.save(newUser);

            Profile newUserProfile = Profile.builder().profileUser(newUser).build();
            profileDAO.save(newUserProfile);
            ProfileCard newUserProfileCard = ProfileCard.builder().profile(newUserProfile).build();
            profileCardDAO.save(newUserProfileCard);

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
            System.out.println("Authorities" + foundUser.getAuthorities());
            return true;
        }
        return false;
    }


}
