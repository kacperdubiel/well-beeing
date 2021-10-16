package com.wellbeeing.wellbeeing.api;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.diet.ProfileCardService;
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
    private final ProfileCardService profileCardService;

    @Autowired
    public ProfileCardController(@Qualifier("profileCardService") ProfileCardService profileCardService){
        this.profileCardService = profileCardService;
    }

    @RequestMapping(path = "/profile-card/{profileCardId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileCardById(@PathVariable("profileCardId") UUID profileCardId){
        try {
            ProfileCard profileCard = profileCardService.getProfileCardById(profileCardId);
            if (profileCard == null) {
                return new ResponseEntity<>(new ErrorMessage("Profile card not found", "404"),
                        HttpStatus.NOT_FOUND);
            } else
                return new ResponseEntity<>(profileCard, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile-card/{profileCardId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProfileCardById(@PathVariable("profileCardId") UUID profileCardId,
                                                   @RequestBody @NonNull ProfileCard profileCard){
        try {
            ProfileCard actProfileCard = profileCardService.updateProfileCard(profileCard, profileCardId);
            if (profileCard == null) {
                return new ResponseEntity<>(new ErrorMessage("Profile card not found", "404"),
                        HttpStatus.NOT_FOUND);
            } else
                return new ResponseEntity<>(actProfileCard, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/profile-card/{profileCardId}/diet-calculation", method = RequestMethod.GET)
    public ResponseEntity<?> getDietCalcForProfileCardById(@PathVariable("profileCardId") UUID profileCardId){
        try {
            ProfileDietCalculation dietCalc = profileCardService.getProfileDietCalculationByProfileCardId(profileCardId);
            if (dietCalc == null) {
                return new ResponseEntity<>(new ErrorMessage("Profile card not found", "404"),
                        HttpStatus.NOT_FOUND);
            } else
                return new ResponseEntity<>(dietCalc, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getCause().toString(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
