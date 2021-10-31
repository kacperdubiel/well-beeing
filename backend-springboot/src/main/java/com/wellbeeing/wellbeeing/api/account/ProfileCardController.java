package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.service.account.ProfileCardService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileCardController {
    private ProfileCardService profileCardService;
    private UserService userService;

    @Autowired
    public ProfileCardController(@Qualifier("profileCardService") ProfileCardService profileCardService,
                                 @Qualifier("userService") UserService userService){
        this.profileCardService = profileCardService;
        this.userService = userService;
    }

    @RequestMapping(path = "/profile-card", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileCardByProfileId(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        ProfileCard actProfileCard = profileCardService.getProfileCardByProfileId(profileId);
        return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
    }

    @RequestMapping(path = "/profile-card", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileCardByProfileId(Principal principal,
                                                          @RequestBody @NonNull ProfileCard profileCard) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        ProfileCard actProfileCard = profileCardService.updateProfileCardByProfileId(profileCard, profileId);
        return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
    }
}
