package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.service.diet.calculation.ProfileDietCalculationService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProfileDietCalculationController {
    private ProfileDietCalculationService profileDietCalculationService;

    @Autowired
    public ProfileDietCalculationController(@Qualifier("profileDietCalculationService")ProfileDietCalculationService
                                                    profileDietCalculationService){
        this.profileDietCalculationService = profileDietCalculationService;
    }

    @RequestMapping(path = "/diet-calc/profile/{profileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileDietCalculationByProfileId(@PathVariable("profileId") UUID profileId) throws NotFoundException {
        ProfileDietCalculation dietCalc = profileDietCalculationService.getDietCalculationByProfileId(profileId);
        return new ResponseEntity<>(dietCalc, HttpStatus.OK);
    }
}
