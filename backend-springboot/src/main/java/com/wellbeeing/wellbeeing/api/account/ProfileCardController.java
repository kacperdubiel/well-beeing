package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.account.ProfileCardService;
import javassist.NotFoundException;
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
    public ResponseEntity<?> getProfileCardByProfileId(@PathVariable("profileId") UUID profileId){
        try {
            ProfileCard actProfileCard = profileCardService.getProfileCardByProfileId(profileId);
            return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("NotFound" + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile-card/profile/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileCardByProfileId(@PathVariable("profileId") UUID profileId,
                                                          @RequestBody @NonNull ProfileCard profileCard){
        try {
            ProfileCard actProfileCard = profileCardService.updateProfileCardByProfileId(profileCard, profileId);
            return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Not found" + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
