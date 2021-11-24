package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;
import com.wellbeeing.wellbeeing.domain.message.diet.AddNutritionPlanOwnerRequest;
import com.wellbeeing.wellbeeing.domain.message.diet.CreateNutritionPlanRequest;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.plan.NutritionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NutritionPlanController {

    private final NutritionPlanService nutritionPlanService;
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public NutritionPlanController(@Qualifier("nutritionPlanService") NutritionPlanService nutritionPlanService,
                                   @Qualifier("userService") UserService userService,
                                   @Qualifier("profileService") ProfileService profileService){
        this.nutritionPlanService = nutritionPlanService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "/nutrition-plan/created", method = RequestMethod.GET)
    public ResponseEntity<?> getUserCreatedNutritionPlans(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        List<NutritionPlan> nutritionPlans = nutritionPlanService.getAllCreatedByUserProfileNutritionPlans(profileId);
        return new ResponseEntity<>(nutritionPlans, HttpStatus.OK);
    }

    @RolesAllowed(ERole.Name.ROLE_DIETICIAN)
    @RequestMapping(path = "/nutrition-plan/created/dietician", method = RequestMethod.GET)
    public ResponseEntity<?> getDieticianCreatedNutritionPlans(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        List<NutritionPlan> nutritionPlans = nutritionPlanService.getAllCreatedByDieticianProfileNutritionPlans(profileId);
        return new ResponseEntity<>(nutritionPlans, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/owned", method = RequestMethod.GET)
    public ResponseEntity<?> getSuggestedNutritionPlans(Principal principal) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        List<NutritionPlan> nutritionPlans = nutritionPlanService.getAllOwnedByUserProfileNutritionPlans(profileId);
        return new ResponseEntity<>(nutritionPlans, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/owned", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOwnedNutritionPlan(Principal principal,
                                                 @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, ForbiddenException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan np = nutritionPlanService.getNutritionPlanById(nutritionPlanId);
        if(!profileId.equals(np.getOwnerProfile().getId()) && !np.getOwnerProfile().getId().equals(np.getCreatorProfile().getId()))
            throw new ForbiddenException("Access to delete nutrition plan with id: " + nutritionPlanId + " forbidden");
        if(np.isMain()){
            throw new ConflictException("Access to delete forbidden, plan with id " + nutritionPlanId + "is marked as main");
        }
        boolean isDeleted = nutritionPlanService.deleteOwnedNutritionPlanFromProfile(nutritionPlanId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/created", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCreatedNutritionPlan(Principal principal,
                                                 @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, ForbiddenException, ConflictException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan np = nutritionPlanService.getNutritionPlanById(nutritionPlanId);
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getCreatorProfile().getId()))
            throw new ForbiddenException("Access to delete nutrition plan with id: " + nutritionPlanId + " forbidden");
        if(np.isMain()){
            throw new ConflictException("Access to delete forbidden, plan with id " + nutritionPlanId + "is marked as main");
        }
        boolean isDeleted = nutritionPlanService.deleteCreatedNutritionPlanFromProfile(nutritionPlanId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/main", method = RequestMethod.POST)
    public ResponseEntity<?> changeNutritionPlanMain(Principal principal,
                                                 @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getOwnerProfile().getId()))
            throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        NutritionPlan np = nutritionPlanService.changeNutritionPlanMain(nutritionPlanId, profileId);
        return new ResponseEntity<>(np, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/owner", method = RequestMethod.POST)
    public ResponseEntity<?> addNutritionPlanOwner(Principal principal,
                                                   @PathVariable("nutritionPlanId") UUID nutritionPlanId,
                                                   @RequestBody @NonNull AddNutritionPlanOwnerRequest addNutritionPlanOwnerRequest) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        Profile owner = profileService.getProfileById(addNutritionPlanOwnerRequest.getNutritionPlanOwnerId());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getCreatorProfile().getId()))
            throw new ForbiddenException("Access to add owner to nutrition plan with id: " + nutritionPlanId + " forbidden");
        NutritionPlan np = nutritionPlanService.addOwnerToNutritionPlan(nutritionPlanId, owner);
        return new ResponseEntity<>(np, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/owner", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNutritionPlanOwner(Principal principal,
                                                   @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        if(!profileId.equals(nutritionPlanService.getNutritionPlanById(nutritionPlanId).getCreatorProfile().getId()))
            throw new ForbiddenException("Access to add owner to nutrition plan with id: " + nutritionPlanId + " forbidden");
        NutritionPlan np = nutritionPlanService.deleteOwnerFromNutritionPlan(nutritionPlanId);
        return new ResponseEntity<>(np, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/generate", method = RequestMethod.GET)
    public ResponseEntity<?> generateNutritionPlan(Principal principal) throws NotFoundException, NutritionPlanGenerationException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.generateNutritionPlanForProfile(profileId);
        return new ResponseEntity<>(nutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan", method = RequestMethod.POST)
    public ResponseEntity<?> addEmptyNutritionPlan(Principal principal, @RequestBody @NonNull CreateNutritionPlanRequest request) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.addEmptyNutritionPlanToProfile(profileId, request.getName());
        return new ResponseEntity<>(nutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/dietician", method = RequestMethod.POST)
    public ResponseEntity<?> addEmptyDieticianNutritionPlan(Principal principal, @RequestBody @NonNull CreateNutritionPlanRequest request) throws NotFoundException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.addEmptyNutritionPlanToDieticianProfile(profileId, request.getName());
        return new ResponseEntity<>(nutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{planId}", method = RequestMethod.GET)
    public ResponseEntity<?> getNutritionPlanById(Principal principal, @PathVariable("planId") UUID planId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.getNutritionPlanById(planId);
        if ((!profileId.equals(nutritionPlan.getCreatorProfile().getId()))){
            if(((nutritionPlan.getOwnerProfile() == null) || (!profileId.equals(nutritionPlan.getOwnerProfile().getId()))))
                throw new ForbiddenException("Access to nutrition plan with id: " + planId + " forbidden");
        }
        return new ResponseEntity<>(nutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/position", method = RequestMethod.POST)
    public ResponseEntity<?> addNutritionPlanPosition(Principal principal,
                                              @RequestBody @NonNull NutritionPlanPosition nutritionPlanPos,
                                              @PathVariable("nutritionPlanId") UUID nutritionPlanId) throws NotFoundException, NutritionPlanGenerationException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.getNutritionPlanById(nutritionPlanId);
        if ((!profileId.equals(nutritionPlan.getCreatorProfile().getId()))){
            if(((nutritionPlan.getOwnerProfile() == null) || (!profileId.equals(nutritionPlan.getOwnerProfile().getId()))))
                throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        }
        NutritionPlan newNutritionPlan = nutritionPlanService.addPositionToProfileNutritionPlan(nutritionPlanPos, nutritionPlanId);
        return new ResponseEntity<>(newNutritionPlan, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-plan/{nutritionPlanId}/position/{positionId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNutritionPlanPosition(Principal principal,
                                                         @PathVariable("nutritionPlanId") UUID nutritionPlanId,
                                                         @PathVariable("positionId") UUID positionId) throws NotFoundException, ForbiddenException {
        UUID profileId = userService.findUserIdByUsername(principal.getName());
        NutritionPlan nutritionPlan = nutritionPlanService.getNutritionPlanById(nutritionPlanId);
        if ((!profileId.equals(nutritionPlan.getCreatorProfile().getId()))){
            if(((nutritionPlan.getOwnerProfile() == null) || (!profileId.equals(nutritionPlan.getOwnerProfile().getId()))))
                throw new ForbiddenException("Access to nutrition plan with id: " + nutritionPlanId + " forbidden");
        }
        NutritionPlan newNutritionPlan = nutritionPlanService.deletePositionFromProfileNutritionPlan(positionId, nutritionPlanId);
        return new ResponseEntity<>(newNutritionPlan, HttpStatus.OK);
    }
}
