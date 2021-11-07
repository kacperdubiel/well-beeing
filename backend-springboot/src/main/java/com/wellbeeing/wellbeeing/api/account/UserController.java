package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.PasswordException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.RoleToUserIdRequest;
import com.wellbeeing.wellbeeing.domain.message.RoleToUserRequest;
import com.wellbeeing.wellbeeing.service.account.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

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

    @PostMapping("/user/update-password")
    @RolesAllowed(ERole.Name.ROLE_BASIC_USER)
    public ResponseEntity<?> changeUserPassword(Locale locale,
                                              @RequestParam("password") String password,
                                              @RequestParam("oldPassword") String oldPassword) throws PasswordException {
        User user = userService.loadUserByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            throw new PasswordException("Old password is incorrect!");
        }

        if (Objects.equals(oldPassword, password)) {
            throw new PasswordException("New password can't be the same as old.");
        }

        userService.changeUserPassword(user, password);
        return new ResponseEntity<>("Password updated", HttpStatus.OK);
    }

    @PostMapping("/user/update-email")
    @RolesAllowed(ERole.Name.ROLE_BASIC_USER)
    public ResponseEntity<?> changeUserEmail(Principal principal, @RequestParam("email") String email) throws ConflictException {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        userService.changeUserEmail(userId, email);
        return new ResponseEntity<>("Email updated", HttpStatus.OK);
    }

    @RequestMapping(path = "/add-role-to-user", method = RequestMethod.POST)
    public ResponseEntity<?> addRoleToUser(@RequestBody @NonNull RoleToUserRequest roleToUserRequest){
        if(!userService.addRoleToUser(roleToUserRequest.getUsername(), roleToUserRequest.getRole())) {
            return new ResponseEntity<>(new ErrorMessage("Can't set this role to user!", "error"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Roles updated", HttpStatus.OK);
    }

    @RequestMapping(path = "/add-role-to-user-id", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> addRoleToUserId(@RequestBody @NonNull RoleToUserIdRequest roleToUserIdRequest){
        if(!userService.addRoleToUser(roleToUserIdRequest.getUserId(), roleToUserIdRequest.getRole())) {
            return new ResponseEntity<>(new ErrorMessage("Can't set this role to user!", "error"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Roles updated", HttpStatus.OK);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("hlo", HttpStatus.OK);
    }



}
