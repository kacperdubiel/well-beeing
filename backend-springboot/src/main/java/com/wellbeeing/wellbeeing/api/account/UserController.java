package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.RoleToUserRequest;
import com.wellbeeing.wellbeeing.service.account.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {
    private UserService userService;

    public UserController(@Qualifier("userService") UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @NonNull User user){
        if(!userService.register(user))
            return new ResponseEntity<>(new ErrorMessage("Account already exist", "error"), HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/add-role-to-user", method = RequestMethod.POST)
    public ResponseEntity<?> addRoleToUser(@RequestBody @NonNull RoleToUserRequest roleToUserRequest){
        if(!userService.addRoleToUser(roleToUserRequest.getUsername(), roleToUserRequest.getRole())) {
            return new ResponseEntity<>(new ErrorMessage("Can't set this role to user!", "error"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Roles updated", HttpStatus.OK);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("hlo", HttpStatus.OK);
    }



}
