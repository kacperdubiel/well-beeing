package com.wellbeeing.wellbeeing.service;

import com.wellbeeing.wellbeeing.domain.User;
import com.wellbeeing.wellbeeing.repository.UserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService, UserServiceApi {
    private UserDAO userDAO;

    public UserService(@Qualifier("userDAO") UserDAO userDAO){
        this.userDAO = userDAO;
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
            userDAO.save(new User(user.getUsername(), user.getPassword()));
            return true;
        }
        return false;
    }
}
