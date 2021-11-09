package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.exception.UnauthorizedException;
import com.wellbeeing.wellbeeing.domain.message.AuthenticationRequest;
import com.wellbeeing.wellbeeing.domain.message.AuthenticationResponse;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private UserDetailsService userService;
    private JwtUtil jwtUtil;


    public AuthController(@Qualifier("userService") UserDetailsService userService,
                          @Qualifier("jwtUtil") JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @NonNull AuthenticationRequest authenticationRequest) throws UnauthorizedException {

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(), authenticationRequest.getPassword(), userDetails.getAuthorities()));
        }
        catch (BadCredentialsException e){
            throw new UnauthorizedException("Bad credentials");
        }
        final String jwt = jwtUtil.generateToken(userDetails);
        ArrayList<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(authority -> {
            roles.add(((Role) authority).getRole().toString());
        });
        return ResponseEntity.ok(new AuthenticationResponse(jwt, roles));
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationTokenAdmin(@RequestBody @NonNull AuthenticationRequest authenticationRequest){

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
        AtomicBoolean isAdmin = new AtomicBoolean(false);
        userDetails.getAuthorities().forEach(authority -> {
            if (((Role) authority).getRole().equals(ERole.ROLE_ADMIN))
                isAdmin.set(true);
        });

        if (!isAdmin.get())
            return new ResponseEntity<>(new ErrorMessage("Unauthorized", "error"), HttpStatus.UNAUTHORIZED);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(), authenticationRequest.getPassword(), userDetails.getAuthorities()));
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<>(new ErrorMessage("Unauthorized", "error"), HttpStatus.UNAUTHORIZED);
        }
        final String jwt = jwtUtil.generateToken(userDetails);
        ArrayList<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(authority -> {
            roles.add(((Role) authority).getRole().toString());
        });
        return ResponseEntity.ok(new AuthenticationResponse(jwt, roles));
    }

}
