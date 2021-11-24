package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.PasswordException;
import com.wellbeeing.wellbeeing.domain.message.AuthenticationResponse;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.RoleToUserIdRequest;
import com.wellbeeing.wellbeeing.domain.message.RoleToUserRequest;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {
    private UserService userService;
    private JwtUtil jwtUtil;

    public UserController(@Qualifier("userService") UserService userService, @Qualifier("jwtUtil")JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @NonNull User user) throws ConflictException {
        if(!userService.register(user))
            throw new ConflictException("Account already exists");
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
//        User user = userService.loadUserByEmail(principal.getName());
        User user = userService.changeUserEmail(userId, email);
        final String jwt = jwtUtil.generateToken(user);
        ArrayList<String> roles = new ArrayList<>();
        user.getAuthorities().forEach(authority -> {
            roles.add(((Role) authority).getRole().toString());
        });
        return new ResponseEntity<>(new AuthenticationResponse(jwt, roles), HttpStatus.OK);
    }

    @RequestMapping(path = "/add-role-to-user", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> addRoleToUser(@RequestBody @NonNull RoleToUserRequest roleToUserRequest) throws ConflictException {
        if(!userService.addRoleToUser(roleToUserRequest.getUsername(), roleToUserRequest.getRole())) {
            throw new ConflictException("Role cannot be assigned to user");
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
