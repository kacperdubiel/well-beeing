package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.calculation.ProfileDietCalculationService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
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

    @Autowired
    public ProfileDietCalculationController(@Qualifier("profileDietCalculationService")ProfileDietCalculationService
                                                    profileDietCalculationService,
                                            @Qualifier("userService") UserService userService){
        this.profileDietCalculationService = profileDietCalculationService;
        this.userService = userService;
    }

    @RequestMapping(path = "/diet-calc", method = RequestMethod.GET)
    public ResponseEntity<?> getProfileDietCalculationByProfileId(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        ProfileDietCalculation dietCalc = profileDietCalculationService.getDietCalculationByProfileId(profileId);
        return new ResponseEntity<>(dietCalc, HttpStatus.OK);
    }
}
