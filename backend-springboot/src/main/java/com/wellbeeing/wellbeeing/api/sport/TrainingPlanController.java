package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.sport.*;
import com.wellbeeing.wellbeeing.domain.sport.*;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.TrainingPlanService;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/training-plan")
public class TrainingPlanController {
    private final TrainingPlanService trainingPlanService;
    private UserDAO userDAO;

    public TrainingPlanController(@Qualifier("trainingPlanService") TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getTrainingPlanById(@PathVariable(value = "id") Long trainingPlanId, Principal principal) throws NotFoundException {
        return new ResponseEntity<>(trainingPlanService.getTrainingPlan(trainingPlanId, principal.getName()), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getTrainingPlans() {
        return new ResponseEntity<>(trainingPlanService.getAllTrainingPlans(), HttpStatus.OK);
    }

    @GetMapping(path = "/my")
    public ResponseEntity<?> getMyTrainingPlans(Principal principal) throws NotFoundException {
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
            createdTrainingPlan = trainingPlanService.addTrainingPlan(request.getTrainingPlan(), principal.getName(), request.getOwnerId());
        if (createdTrainingPlan == null) {
            return new ResponseEntity<>("Couldn't create training plan!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(createdTrainingPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable(value = "id") Long trainingPlanId ) throws NotFoundException {
            trainingPlanService.deleteTrainingPlan(trainingPlanId);
        return new ResponseEntity<>("Successfully deleted training plan with id=" + trainingPlanId, HttpStatus.OK);
    }

    @PatchMapping("/{id}/add-position")
    public ResponseEntity<?> addPositionToTrainingPlan(@PathVariable(value = "id")Long trainingPlanId, @RequestBody @NonNull AddTrainingToPlanRequest request, Principal principal) throws NotFoundException, IllegalArgumentException {
        TrainingPosition trainingPosition = null;
        trainingPosition = trainingPlanService.addPositionToTrainingPlan(trainingPlanId, request.getTrainingId(), request.getDate(), request.getTimeOfDay(), principal.getName());


        return new ResponseEntity<>(trainingPosition, HttpStatus.OK);

    }

    @PatchMapping("/{id}/remove-position/{positionId}")
    public ResponseEntity<?> removePositionFromTrainingPlan(@PathVariable(value = "id")Long trainingPlanId, @PathVariable(value = "positionId")Long positionId, Principal principal) throws NotFoundException {

        trainingPlanService.removePositionFromTrainingPlan(trainingPlanId, positionId, principal.getName());
        return new ResponseEntity<>("Position removed!", HttpStatus.OK);

    }
    @PatchMapping("/{id}/add-many-positions/")
    public ResponseEntity<?> addManyPositionsToTrainingPlan(@PathVariable(value = "id")Long trainingPlanId,@RequestBody @NonNull List<AddTrainingToPlanRequest> requests, Principal principal) {
        List<TrainingPosition> addedPositions = new ArrayList<>();
        for (AddTrainingToPlanRequest request:requests) {
            TrainingPosition trainingPosition;
            try {
                trainingPosition = trainingPlanService.addPositionToTrainingPlan(trainingPlanId, request.getTrainingId(), request.getDate(), request.getTimeOfDay(), principal.getName());
            } catch (NotFoundException | IllegalArgumentException e) {
                return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Error"), HttpStatus.NOT_FOUND);
            }
            addedPositions.add(trainingPosition);
        }
        return new ResponseEntity<>(addedPositions, HttpStatus.OK);
    }


    @RequestMapping("/generate-plan-for/{id}")
    public ResponseEntity<?> generateTrainingPlanForId(@PathVariable(value = "id") UUID userId, @RequestBody @NonNull PlanGeneratorRequest request) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/send-request")
    public ResponseEntity<?> sendRequestToTrainer(@RequestBody @NonNull MakeRequestToCreateTrainingPlanRequest request, Principal principal) throws NotFoundException {
        TrainingPlanRequest newRequest;
        newRequest = trainingPlanService.sendRequestToTrainer(request.getTrainerId(), principal.getName(), request.getMessage());
        return new ResponseEntity<>(newRequest, HttpStatus.OK);
    }

    @GetMapping("/get-my-requests")
    public ResponseEntity<?> getUserRequests(Principal principal) throws NotFoundException {
        List<TrainingPlanRequest> newRequest;
        newRequest = trainingPlanService.getMyRequests(principal.getName());
        return new ResponseEntity<>(newRequest, HttpStatus.OK);
    }
    @RolesAllowed(ERole.Name.ROLE_TRAINER)
    @GetMapping("/get-trainer-requests")
    public ResponseEntity<?> getTrainerRequests(Principal principal) throws NotFoundException {
        List<TrainingPlanRequest> newRequest;
        newRequest = trainingPlanService.getTrainersRequests(principal.getName());
        return new ResponseEntity<>(newRequest, HttpStatus.OK);
    }

    @PatchMapping("/request")
    public ResponseEntity<?> updateRequestStatus(@RequestBody @NonNull ChangeTrainingPlanRequestStatusRequest request, Principal principal) throws NotFoundException {
        TrainingPlanRequest editedRequest;
        editedRequest = trainingPlanService.changeTrainingPlanRequestStatus(principal.getName(), request.getRequestId(), request.getNewStatus());

        return new ResponseEntity<>(editedRequest, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateTrainingPlan(@PathVariable(value = "id") Long trainingPlanId, @RequestBody Map<String, Object> fields, Principal principal) throws Exception {
        // Sanitize and validate the data
        if (trainingPlanId <= 0 || fields == null || fields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Invalid claim object received or invalid id or id does not match object
        }

        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlan(trainingPlanId, principal.getName());

        // Does the object exist?
        if (trainingPlan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
        }

        // Remove id from request, we don't ever want to change the id.
        // This is not necessary, you can just do it to save time on the reflection
        // loop used below since we checked the id above
        fields.remove("trainingPlanId");
        fields.remove("trainingPositions");
        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(TrainingPlan.class, k); // find field in the object class
            field.setAccessible(true);
            if (field.getType() == EPlanStatus.class)
                v = EPlanStatus.valueOf((String) v);
            ReflectionUtils.setField(field, trainingPlan, v); // set given field for defined object to value V
        });

        TrainingPlan updated = trainingPlanService.partialUpdateTrainingPlan(trainingPlan);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
