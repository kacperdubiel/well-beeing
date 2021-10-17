package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.Role;
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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private UserDetailsService userService;
    private JwtUtil jwtUtil;


    public AuthController(@Qualifier("userService") UserDetailsService userService,
                          @Qualifier("jwtUtil") JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @NonNull AuthenticationRequest authenticationRequest){

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
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
