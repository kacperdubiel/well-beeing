package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.plan.NutritionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NutritionPlanController {

    private NutritionPlanService nutritionPlanService;
    private UserService userService;

    @Autowired
    public NutritionPlanController(@Qualifier("nutritionPlanService") NutritionPlanService nutritionPlanService,
                                   @Qualifier("userService") UserService userService){
        this.nutritionPlanService = nutritionPlanService;
        this.userService = userService;
    }

    @RequestMapping(path = "/nutrition-plan", method = RequestMethod.GET)
    public ResponseEntity<?> getNutritionPlans(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        List<NutritionPlan> nutritionPlans = nutritionPlanService.getAllProfileNutritionPlans(profileId);
        return new ResponseEntity<>(nutritionPlans, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteNutritionPlan(Principal principal,
                                                 @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getId()))
            throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        boolean isDeleted = nutritionPlanService.deleteNutritionPlanFromProfile(nutritionPlanId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/generate", method = RequestMethod.GET)
    public ResponseEntity<?> generateNutritionPlan(Principal principal) throws NotFoundException, NutritionPlanGenerationException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.generateNutritionPlanForProfile(profileId);
        return new ResponseEntity<>(nutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/initialize", method = RequestMethod.POST)
    public ResponseEntity<?> addEmptyNutritionPlan(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.addEmptyNutritionPlanToProfile(profileId);
        return new ResponseEntity<>(nutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan", method = RequestMethod.POST)
    public ResponseEntity<?> addNutritionPlan(Principal principal, @RequestBody @NonNull NutritionPlan nutritionPlan) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan addedNutritionPlan = nutritionPlanService.addNutritionPlanToProfile(nutritionPlan, profileId);
        return new ResponseEntity<>(addedNutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/position", method = RequestMethod.POST)
    public ResponseEntity<?> addNutritionPlanPosition(Principal principal,
                                              @RequestBody @NonNull NutritionPlanPosition nutritionPlanPos,
                                              @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, NutritionPlanGenerationException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getId()))
            throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        NutritionPlan newNutritionPlan = nutritionPlanService.addPositionToProfileNutritionPlan(nutritionPlanPos, nutritionPlanId);
        return new ResponseEntity<>(newNutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/position/{positionId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateNutritionPlanPosition(Principal principal,
                                                      @RequestBody @NonNull NutritionPlanPosition nutritionPlanPos,
                                                      @PathVariable("nutritionPlanId") UUID nutritionPlanId,
                                                      @PathVariable("positionId") UUID positionId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getId()))
            throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        nutritionPlanPos.setId(positionId);
        NutritionPlan newNutritionPlan = nutritionPlanService.updatePositionInProfileNutritionPlan(nutritionPlanPos, nutritionPlanId);
        return new ResponseEntity<>(newNutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/position/{positionId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNutritionPlanPosition(Principal principal,
                                                         @PathVariable("nutritionPlanId") UUID nutritionPlanId,
                                                         @PathVariable("positionId") UUID positionId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getId()))
            throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        NutritionPlan newNutritionPlan = nutritionPlanService.deletePositionFromProfileNutritionPlan(positionId, nutritionPlanId);
        return new ResponseEntity<>(newNutritionPlan, HttpStatus.OK);
    }
}
