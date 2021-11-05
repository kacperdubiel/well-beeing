package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/profile")
@RestController
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public ProfileController(@Qualifier("profileService") ProfileService profileService,
                             @Qualifier("userService") UserService userService){
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getProfile(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile profile = profileService.getProfileById(profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PatchMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateProfileBy(Principal principal,
                                                    @RequestBody Map<String, Object> fields) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile actProfile = profileService.partialUpdateProfile(profileId, fields);
        return new ResponseEntity<>(actProfile, HttpStatus.OK);
    }
}
