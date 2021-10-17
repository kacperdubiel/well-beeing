package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.*;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.RoleDAO;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProfileDietCalculationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    private UserDAO userDAO;
    private ProfileDAO profileDAO;
    private ProfileCardDAO profileCardDAO;
    private ProfileDietCalculationDAO dietCalculationDAO;
    private RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO,
                           @Qualifier("roleDAO") RoleDAO roleDAO,
                           @Qualifier("profileDAO") ProfileDAO profileDAO,
                           @Qualifier("profileCardDAO") ProfileCardDAO profileCardDAO,
                           @Qualifier("profileDietCalculationDAO") ProfileDietCalculationDAO profileDietCalculationDAO){
        this.userDAO = userDAO;
        this.profileDAO = profileDAO;
        this.profileCardDAO = profileCardDAO;
        this.roleDAO = roleDAO;
        this.dietCalculationDAO = profileDietCalculationDAO;

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

            Profile newUserProfile = Profile.builder().profileUser(newUser).id(newUser.getId()).build();
            profileDAO.save(newUserProfile);
            ProfileCard newUserProfileCard = ProfileCard.builder().profile(newUserProfile).build();
            newUserProfile.setProfileCard(newUserProfileCard);
            profileCardDAO.save(newUserProfileCard);
            profileDAO.save(newUserProfile);

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
