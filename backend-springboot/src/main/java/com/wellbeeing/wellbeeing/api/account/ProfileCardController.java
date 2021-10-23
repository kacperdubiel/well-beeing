package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.account.ProfileCardService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileCardController {
    private ProfileCardService profileCardService;

    @Autowired
    public ProfileCardController(@Qualifier("profileCardService") ProfileCardService profileCardService){
        this.profileCardService = profileCardService;
    }

    @RequestMapping(path = "/profile-card/profile/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileCardByProfileId(@PathVariable("profileId") UUID profileId) throws NotFoundException {
        ProfileCard actProfileCard = profileCardService.getProfileCardByProfileId(profileId);
        return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
    }

    @RequestMapping(path = "/profile-card/profile/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileCardByProfileId(@PathVariable("profileId") UUID profileId,
                                                          @RequestBody @NonNull ProfileCard profileCard) throws NotFoundException {
        ProfileCard actProfileCard = profileCardService.updateProfileCardByProfileId(profileCard, profileId);
        return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
    }
}
