package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.sport.AddExerciseToTrainingRequest;
import com.wellbeeing.wellbeeing.domain.sport.ETrainingDifficulty;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.criteria.JoinType;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/training")
public class TrainingController {
    private final TrainingService trainingService;
    private UserDAO userDAO;


    public TrainingController(@Qualifier("trainingService") TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getTrainingById(@PathVariable(value = "id") Long trainingId, Principal principal) throws NotFoundException {
        return new ResponseEntity<>(trainingService.getTraining(trainingId, principal.getName()), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getTrainingsWithFilters(
            @Join(path = "exerciseInTrainings", alias = "extr", type = JoinType.LEFT)
            @Join(path = "extr.exercise", alias = "ex", type = JoinType.LEFT)
            @Join(path = "ex.labels", alias = "ls", type = JoinType.LEFT)
            @And({
                    @Spec(path = "trainingDifficulty", spec = Equal.class),
                    @Spec(path = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "totalTrainingTimeSeconds", params = {"secondsMoreThan", "secondsLessThan"}, spec = Between.class),
                    @Spec(path = "ex.name", params = "exerciseName", spec = LikeIgnoreCase.class),
                    @Spec(path = "ls.name", params = "label", spec = LikeIgnoreCase.class),
                    @Spec(path = "isDeleted", spec = Equal.class, constVal = "false")
            }) Specification<Training> trainingSpec,
            @PageableDefault(sort = {"name"}, size = 20) Pageable pageable, Principal principal) {
//        return new ResponseEntity<>(trainingService.getAllTrainings(), HttpStatus.OK);
        try {

            return new ResponseEntity<>(trainingService.getAllTrainingsFiltered(trainingSpec, pageable, principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "")
    @RolesAllowed({ERole.Name.ROLE_TRAINER, ERole.Name.ROLE_BASIC_USER})
    public ResponseEntity<?> addTraining(@RequestBody @NonNull Training training, Principal principal) {
        Training createdTraining;
        try {
            createdTraining = trainingService.addTraining(training, principal.getName());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
        if (createdTraining == null) {
            return new ResponseEntity<>("Couldn't create training!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(ERole.Name.ROLE_TRAINER)
    public ResponseEntity<?> deleteTraining(@PathVariable(value = "id") Long trainingId) throws NotFoundException {
        trainingService.deleteTraining(trainingId);
        return new ResponseEntity<>("Successfully deleted training with id=" + trainingId, HttpStatus.OK);
    }

    @PatchMapping("/{id}/remove-exercise/{exerciseId}")
    public ResponseEntity<?> removeExerciseFromTrainingById(@PathVariable(value = "id") Long trainingId, @PathVariable(value = "exerciseId") Long exerciseId, Principal principal) throws NotFoundException {

        trainingService.removeExerciseFromTraining(trainingId, exerciseId, principal.getName());
        return new ResponseEntity<>(String.format("Successfully removed exercise %d from training with id=%d", exerciseId, trainingId), HttpStatus.OK);
    }

    @PatchMapping("/{id}/add-exercise/{exerciseId}")
    public ResponseEntity<?> addExerciseToTrainingById(@PathVariable(value = "id") Long trainingId, @PathVariable(value = "exerciseId") Long exerciseId, @RequestBody @NonNull AddExerciseToTrainingRequest request, Principal principal) throws NotFoundException {
        ExerciseInTraining addedExercise = null;
        addedExercise = trainingService.addExerciseToTraining(trainingId, exerciseId, request.getReps(),
                request.getTime_seconds(), request.getSeries(),
                principal.getName());
        if (addedExercise == null) {
            return new ResponseEntity<>(new ErrorMessage(String.format("Couldn't add exercise %d to training with id=%d", exerciseId, trainingId), "Error"), HttpStatus.OK);
        }
        return new ResponseEntity<>(addedExercise, HttpStatus.OK);
    }

    @GetMapping("/calories-burned/{trainingId}")
    public ResponseEntity<?> getCaloriesBurnedFromClient(@PathVariable(value = "trainingId") long trainingId, Principal principal) throws NotFoundException {
        double caloriesBurned;
        caloriesBurned = trainingService.getCaloriesBurnedFromUser(trainingId, principal.getName());
        if (caloriesBurned == -1) {
            return new ResponseEntity<>(new ErrorMessage("", ""), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(caloriesBurned, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateExercise(@PathVariable(value = "id") Long trainingId, @RequestBody Map<String, Object> fields, Principal principal) throws Exception {
        // Sanitize and validate the data
        if (trainingId <= 0 || fields == null || fields.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Invalid claim object received or invalid id or id does not match object
        }

        Training training = trainingService.getTraining(trainingId, principal.getName());

        // Does the object exist?
        if (training == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
        }

        // Remove id from request, we don't ever want to change the id.
        // This is not necessary, you can just do it to save time on the reflection
        // loop used below since we checked the id above
        fields.remove("trainingId");
        fields.remove("exerciseInTrainings");
        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(Training.class, k); // find field in the object class
            if (field != null) {
                field.setAccessible(true);

                if (field.getType() == ETrainingDifficulty.class)
                    v = ETrainingDifficulty.valueOf((String) v);
                ReflectionUtils.setField(field, training, v); // set given field for defined object to value V
            }

        });

        Training updated = trainingService.partialUpdateTraining(training);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
