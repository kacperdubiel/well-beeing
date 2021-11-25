package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.service.account.ProfileCardService;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
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
    private final ProfileCardService profileCardService;
    private final ProfileService profileService;
    private final UserService userService;
    private final ProfileConnectionService profileConnectionService;

    @Autowired
    public ProfileCardController(@Qualifier("profileCardService") ProfileCardService profileCardService,
                                 @Qualifier("userService") UserService userService,
                                 @Qualifier("profileService") ProfileService profileService,
                                 @Qualifier("profileConnectionService") ProfileConnectionService profileConnectionService){
        this.profileCardService = profileCardService;
        this.userService = userService;
        this.profileService = profileService;
        this.profileConnectionService = profileConnectionService;
    }

    @RequestMapping(path = "/profile-card", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileCardMy(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        ProfileCard actProfileCard = profileCardService.getProfileCardByProfileId(profileId);
        return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
    }

    @RequestMapping(path = "/profile-card/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileCardByProfileId(Principal principal, @PathVariable("profileId") UUID profileId) throws NotFoundException, ForbiddenException {
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        Profile dieticianProfile = profileService.getProfileById(dieticianId);
        Profile userProfile = profileService.getProfileById(profileId);
        ProfileConnection pc = profileConnectionService.getProfileConnectionByProfileAndConnectedWithAndTypeAndIsAccepted(userProfile, dieticianProfile, EConnectionType.WITH_DIETICIAN);
        if(pc != null)
            return new ResponseEntity<>(profileCardService.getProfileCardByProfileId(profileId), HttpStatus.OK);
        throw new ForbiddenException("There is no connection with profile with id" + profileId);
    }

    @RequestMapping(path = "/profile-card", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileCardByProfileId(Principal principal,
                                                          @RequestBody @NonNull ProfileCard profileCard) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        ProfileCard actProfileCard = profileCardService.updateProfileCardByProfileId(profileCard, profileId);
        return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
    }
}
