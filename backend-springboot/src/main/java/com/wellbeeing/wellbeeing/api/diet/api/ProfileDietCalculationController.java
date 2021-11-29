package com.wellbeeing.wellbeeing.api.diet.api;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.calculation.ProfileDietCalculationService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileDietCalculationController {

    private ProfileDietCalculationService profileDietCalculationService;
    private UserService userService;
    private ProfileService profileService;
    private ProfileConnectionService profileConnectionService;

    @Autowired
    public ProfileDietCalculationController(@Qualifier("profileDietCalculationService")ProfileDietCalculationService
                                                    profileDietCalculationService,
                                            @Qualifier("userService") UserService userService,
                                            @Qualifier("profileService") ProfileService profileService,
                                            @Qualifier("profileConnectionService") ProfileConnectionService profileConnectionService){
        this.profileDietCalculationService = profileDietCalculationService;
        this.userService = userService;
        this.profileService = profileService;
        this.profileConnectionService = profileConnectionService;
    }

    @RequestMapping(path = "/diet-calc", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileDietCalculation(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        ProfileDietCalculation dietCalc = profileDietCalculationService.getDietCalculationByProfileId(profileId);
        return new ResponseEntity<>(dietCalc, HttpStatus.OK);
    }

    @RequestMapping(path = "/diet-calc/profile/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileDietCalculationByProfileId(Principal principal, @PathVariable("profileId") UUID profileId) throws NotFoundException, ForbiddenException {
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        Profile dieticianProfile = profileService.getProfileById(dieticianId);
        Profile userProfile = profileService.getProfileById(profileId);

        ProfileConnection pc = profileConnectionService.getProfileConnectionByProfileAndConnectedWithAndTypeAndIsAccepted(userProfile, dieticianProfile, EConnectionType.WITH_DIETICIAN);
        if(pc != null){
            ProfileDietCalculation dietCalc = profileDietCalculationService.getDietCalculationByProfileId(profileId);
            return new ResponseEntity<>(dietCalc, HttpStatus.OK);
        }
        throw new ForbiddenException("There is no connection with profile with id" + profileId);
    }
}
