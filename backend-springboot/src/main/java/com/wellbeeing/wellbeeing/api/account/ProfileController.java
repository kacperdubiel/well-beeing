package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(@Qualifier("profileService") ProfileService profileService){
        this.profileService = profileService;
    }

    @RequestMapping(path = "/profile/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileById(@PathVariable("profileId") UUID profileId){
        try {
            Profile profile = profileService.getProfileById(profileId);
            if (profile == null) {
                return new ResponseEntity<>(new ErrorMessage("Profile not found", "404"),
                        HttpStatus.NOT_FOUND);
            } else
                return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile/{profileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileById(@PathVariable("profileId") UUID profileId,
                                               @NonNull @RequestBody Profile profile){
        try {
            Profile actProfile = profileService.updateProfile(profile, profileId);
            return new ResponseEntity<>(actProfile, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Profile not found" + e.getCause().toString(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile/{profileId}/profile-card", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileCardByProfileId(@PathVariable("profileId") UUID profileId){
        try {
            ProfileCard actProfileCard = profileService.getProfileCardByProfileId(profileId);
            return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Profile not found" + e.getCause().toString(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile/{profileId}/profile-card", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileCardByProfileId(@PathVariable("profileId") UUID profileId,
                                                          @RequestBody @NonNull ProfileCard profileCard){
        try {
            ProfileCard actProfileCard = profileService.updateProfileCardByProfileId(profileCard, profileId);
            return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Profile not found" + e.getCause().toString(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile/{profileId}/diet-calc", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileDietCalc(@PathVariable("profileId") UUID profileId){
        try {
            ProfileDietCalculation dietCalc = profileService.getProfileDietCalculationByProfileId(profileId);
            return new ResponseEntity<>(dietCalc, HttpStatus.OK);
        }
        catch(NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Profile not found" + e.getCause().toString(),
                    "404"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
