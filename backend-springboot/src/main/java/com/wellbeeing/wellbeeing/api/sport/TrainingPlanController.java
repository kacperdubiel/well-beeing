package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.sport.AddTrainingPlanWithOwnerRequest;
import com.wellbeeing.wellbeeing.domain.message.sport.AddTrainingToPlanRequest;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.TrainingPlanService;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/training-plan")
public class TrainingPlanController {
    private TrainingPlanService trainingPlanService;
    private UserDAO userDAO;

    public TrainingPlanController(@Qualifier("trainingPlanService") TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getTrainingPlanById(@PathVariable(value = "id") Long trainingPlanId) {
        return new ResponseEntity<>(trainingPlanService.getTrainingPlan(trainingPlanId), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getTrainingPlans() {
        return new ResponseEntity<>(trainingPlanService.getAllTrainingPlans(), HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getMyTrainingPlans(Principal principal) {
        return new ResponseEntity<>(trainingPlanService.getMyTrainingPlans(principal.getName()), HttpStatus.OK);
    }

    @GetMapping(path = "/owner/{ownerId}")
    public ResponseEntity<?> getTrainingPlansByOwnerId(@PathVariable(value = "ownerId") UUID ownerId) {
        return new ResponseEntity<>(trainingPlanService.getTrainingPlansByOwnerId(ownerId), HttpStatus.OK);
    }

    @GetMapping(path = "/creator/{creatorId}")
    public ResponseEntity<?> getTrainingPlansByCreatorId(@PathVariable(value = "creatorId") UUID creatorId) {
        return new ResponseEntity<>(trainingPlanService.getTrainingPlansByCreatorId(creatorId), HttpStatus.OK);
    }

    @PostMapping(path = "")
    @RolesAllowed({ERole.Name.ROLE_TRAINER, ERole.Name.ROLE_BASIC_USER})
    public ResponseEntity<?> addTrainingPlan(@RequestBody @NonNull AddTrainingPlanWithOwnerRequest request, Principal principal) {
        TrainingPlan createdTrainingPlan;
        try {
            createdTrainingPlan = trainingPlanService.addTrainingPlan(request.getTrainingPlan(), principal.getName(), request.getOwnerId());
        } catch (Exception e) {
            System.out.println("Exception message: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
        if (createdTrainingPlan == null) {
            return new ResponseEntity<>("Couldn't create training plan!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(createdTrainingPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable(value = "id") Long trainingPlanId ) {
        if (!trainingPlanService.deleteTrainingPlan(trainingPlanId)) {
            return new ResponseEntity<>(new ErrorMessage("Couldn't delete training plan with id=" + trainingPlanId, "Error"), HttpStatus.OK);
        }
        return new ResponseEntity<>("Successfully deleted training plan with id=" + trainingPlanId, HttpStatus.OK);
    }

    @PatchMapping("/{id}/add-position")
    public ResponseEntity<?> addPositionToTrainingPlan(@PathVariable(value = "id")Long trainingPlanId, @RequestBody @NonNull AddTrainingToPlanRequest request, Principal principal) {
        TrainingPosition trainingPosition = trainingPlanService.addPositionToTrainingPlan(trainingPlanId, request.getTrainingId(), request.getDate(), principal.getName());
        if (trainingPosition == null) {
            return new ResponseEntity<>(new ErrorMessage(String.format("Couldn't add training with id=%d to plan with id=%d",request.getTrainingId(), trainingPlanId), "Error"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(trainingPosition, HttpStatus.OK);

    }

    @PatchMapping("/{id}/remove-position/{positionId}")
    public ResponseEntity<?> removePositionFromTrainingPlan(@PathVariable(value = "id")Long trainingPlanId, @PathVariable(value = "positionId")Long positionId, Principal principal) {
        if (!trainingPlanService.removePositionFromTrainingPlan(trainingPlanId, positionId, principal.getName())) {
            return new ResponseEntity<>(new ErrorMessage(String.format("Couldn't remove position with id=%d from plan with id=%d", positionId, trainingPlanId), "Error"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Position removed!", HttpStatus.OK);

    }
    @PatchMapping("/{id}/add-many-positions/")
    public ResponseEntity<?> addManyPositionsToTrainingPlan(@PathVariable(value = "id")Long trainingPlanId,@RequestBody @NonNull List<AddTrainingToPlanRequest> requests, Principal principal) {
        List<TrainingPosition> addedPositions = new ArrayList<>();
        for (AddTrainingToPlanRequest request:requests) {
            TrainingPosition trainingPosition = trainingPlanService.addPositionToTrainingPlan(trainingPlanId, request.getTrainingId(), request.getDate(), principal.getName());
            addedPositions.add(trainingPosition);
        }
        return new ResponseEntity<>(addedPositions, HttpStatus.OK);
    }
}
