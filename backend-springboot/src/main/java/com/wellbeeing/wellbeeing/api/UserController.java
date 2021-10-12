package com.wellbeeing.wellbeeing.api;

import com.wellbeeing.wellbeeing.domain.User;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.UserServiceApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {
    private UserServiceApi userService;

    public UserController(@Qualifier("userService") UserServiceApi userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @NonNull User user){
        if(!userService.register(user))
            return new ResponseEntity<>(new ErrorMessage("Account already exist", "error"), HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("hlo", HttpStatus.OK);
    }
}
