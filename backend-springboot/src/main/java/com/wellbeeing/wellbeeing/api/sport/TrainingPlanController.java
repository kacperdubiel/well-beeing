package com.wellbeeing.wellbeeing.api.sport;

import com.itextpdf.text.Document;
import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.sport.*;
import com.wellbeeing.wellbeeing.domain.sport.EPlanStatus;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlanRequest;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.PDFFromTrainingPlan;
import com.wellbeeing.wellbeeing.service.sport.TrainingPlanService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/training-plan")
public class TrainingPlanController {
    private final TrainingPlanService trainingPlanService;
    private UserDAO userDAO;

    public TrainingPlanController(@Qualifier("trainingPlanService") TrainingPlanService trainingPlanService,
                                  @Qualifier("userDAO") UserDAO userDAO) {
        this.trainingPlanService = trainingPlanService;
        this.userDAO = userDAO;
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
        createdTrainingPlan = trainingPlanService.addTrainingPlan(request.getTrainingPlan(), principal.getName(), request.getOwnerId(), request.getRequestId());
        if (createdTrainingPlan == null) {
            return new ResponseEntity<>("Couldn't create training plan!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(createdTrainingPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable(value = "id") Long trainingPlanId) throws NotFoundException {
        trainingPlanService.deleteTrainingPlan(trainingPlanId);
        return new ResponseEntity<>("Successfully deleted training plan with id=" + trainingPlanId, HttpStatus.OK);
    }

    @PatchMapping("/{id}/add-position")
    public ResponseEntity<?> addPositionToTrainingPlan(@PathVariable(value = "id") Long trainingPlanId, @RequestBody @NonNull AddTrainingToPlanRequest request, Principal principal) throws NotFoundException, IllegalArgumentException {
        TrainingPosition trainingPosition = null;
        trainingPosition = trainingPlanService.addPositionToTrainingPlan(trainingPlanId, request.getTrainingId(), request.getDate(), request.getTimeOfDay(), principal.getName());


        return new ResponseEntity<>(trainingPosition, HttpStatus.OK);

    }

    @PatchMapping("/{id}/remove-position/{positionId}")
    public ResponseEntity<?> removePositionFromTrainingPlan(@PathVariable(value = "id") Long trainingPlanId, @PathVariable(value = "positionId") Long positionId, Principal principal) throws NotFoundException {

        trainingPlanService.removePositionFromTrainingPlan(trainingPlanId, positionId, principal.getName());
        return new ResponseEntity<>("Position removed!", HttpStatus.OK);

    }

    @PatchMapping("/{id}/add-many-positions/")
    public ResponseEntity<?> addManyPositionsToTrainingPlan(@PathVariable(value = "id") Long trainingPlanId, @RequestBody @NonNull List<AddTrainingToPlanRequest> requests, Principal principal) {
        List<TrainingPosition> addedPositions = new ArrayList<>();
        for (AddTrainingToPlanRequest request : requests) {
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


    @PostMapping("/generate-plan")
    public ResponseEntity<?> generateTrainingPlanForMe(@RequestBody @NonNull PlanGeneratorRequest request, Principal principal) throws NotFoundException, IllegalArgumentException {
        User requestingUser = userDAO.findUserByEmail(principal.getName()).orElse(null);
        if (requestingUser == null)
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        long id = trainingPlanService.generateTrainingPlanForMe(request.getTrainingsPerDay(),
                request.getActivityGoalId(),
                requestingUser.getProfile(),
                request.getStrategy(),
                request.getBeginningDate(),
                "random"
        );

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> getTrainingPlanRequest(@PathVariable(value = "id") long requestId, Principal principal) throws NotFoundException, ForbiddenException {
        TrainingPlanRequest request = trainingPlanService.getTrainingPlanRequest(requestId, principal.getName());
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PostMapping("/send-request")
    public ResponseEntity<?> sendRequestToTrainer(@RequestBody @NonNull MakeRequestToCreateTrainingPlanRequest request, Principal principal) throws NotFoundException {
        TrainingPlanRequest newRequest;
        newRequest = trainingPlanService.sendRequestToTrainer(request.getTrainerId(), principal.getName(), request.getMessage(), request.getBeginningDate());
        return new ResponseEntity<>(newRequest, HttpStatus.OK);
    }

    @GetMapping("/get-my-requests")
    public ResponseEntity<?> getUserRequests(
            @Join(path = "submitter", alias = "sub")
            @And({
                    @Spec(path = "sub.id", params = "submitter", spec = EqualIgnoreCase.class),
                    @Spec(path = "requestStatus", spec = EqualIgnoreCase.class)
            }) Specification<TrainingPlanRequest> requestSpec,
            @PageableDefault(sort = {"requestDate"}, direction = Sort.Direction.DESC, size = 20) Pageable pageable, Principal principal, @RequestParam UUID submitter) throws NotFoundException {
        User user = userDAO.findUserById(submitter).orElse(null);
        User userPrincipal = userDAO.findUserByEmail(principal.getName()).orElse(null);
        if (!Objects.equals(user, userPrincipal)) {
            throw new NotFoundException("You can't access that data!");
        }
        Page<TrainingPlanRequest> requests;
        requests = trainingPlanService.getMyRequestsFiltered(requestSpec, pageable, principal.getName());
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @RolesAllowed(ERole.Name.ROLE_TRAINER)
    @GetMapping("/get-trainer-requests")
    public ResponseEntity<?> getTrainerRequests(
            @Join(path = "trainer", alias = "tr")
            @And({
                    @Spec(path = "tr.id", params = "trainer", spec = EqualIgnoreCase.class),
                    @Spec(path = "requestStatus", spec = EqualIgnoreCase.class)
            }) Specification<TrainingPlanRequest> requestSpec,
            @PageableDefault(sort = {"requestDate"}, direction = Sort.Direction.DESC, size = 20) Pageable pageable, Principal principal, @RequestParam UUID trainer) throws NotFoundException {
        User user = userDAO.findUserById(trainer).orElse(null);
        User userPrincipal = userDAO.findUserByEmail(principal.getName()).orElse(null);
        if (!Objects.equals(user, userPrincipal)) {
            throw new NotFoundException("You can't access that data!");
        }
        Page<TrainingPlanRequest> requests;
        requests = trainingPlanService.getMyRequestsFiltered(requestSpec, pageable, principal.getName());
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PatchMapping("/request")
    public ResponseEntity<?> updateRequestStatus(@RequestBody @NonNull ChangeTrainingPlanRequestStatusRequest request, Principal principal) throws NotFoundException {
        TrainingPlanRequest editedRequest;
        editedRequest = trainingPlanService.changeTrainingPlanRequestStatus(principal.getName(), request.getRequestId(), request.getNewStatus());

        return new ResponseEntity<>(editedRequest, HttpStatus.OK);
    }

    @PatchMapping("/{id}/update-position-status")
    public ResponseEntity<?> updateTrainingPositionStatus(@PathVariable(value = "id") Long trainingPositionId, @RequestParam String newStatus, Principal principal) throws NotFoundException, IllegalArgumentException {
        return new ResponseEntity<>(trainingPlanService.updateTrainingPositionStatus(trainingPositionId, newStatus, principal.getName()), HttpStatus.OK);
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
            if (k.equals("beginningDate")) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                    v = sdf.parse((String) v);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (field.getType() == EPlanStatus.class)
                v = EPlanStatus.valueOf((String) v);
            ReflectionUtils.setField(field, trainingPlan, v); // set given field for defined object to value V
        });

        TrainingPlan updated = trainingPlanService.partialUpdateTrainingPlan(trainingPlan);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/export/{trainingPlanId}")
    public ResponseEntity<?> exportTrainingPlan(@PathVariable long trainingPlanId, Principal principal) throws IOException, NotFoundException {
        TrainingPlan trainingPlan = trainingPlanService.getTrainingPlan(trainingPlanId, principal.getName());
//        String path = roleRequest.getDocumentImgPath();
        Document doc = PDFFromTrainingPlan.generatePDFFromTrainingPlan(trainingPlan, "someNewPlan");
//        File preFile = new File(path);

        InputStreamResource file = new InputStreamResource(new ByteArrayInputStream(IOUtils.toByteArray(PDFFromTrainingPlan.fileStream)));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "someNewPlan")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(file);
    }
}
