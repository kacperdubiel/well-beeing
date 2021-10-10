package com.wellbeeing.wellbeeing.api;

import com.wellbeeing.wellbeeing.domain.User;
import com.wellbeeing.wellbeeing.service.UserServiceApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserServiceApi userService;

    public UserController(@Qualifier("userService") UserServiceApi userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @NonNull User user){
        userService.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
