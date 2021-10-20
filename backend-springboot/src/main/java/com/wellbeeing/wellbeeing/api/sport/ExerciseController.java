package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.ExerciseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/exercise")
@RestController
public class ExerciseController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private ExerciseService exerciseService;
    private UserDAO userDAO;


    public ExerciseController(@Qualifier("exerciseService") ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getExerciseId(@PathVariable(value = "id") Long exerciseId) {
        return new ResponseEntity<>(exerciseService.getExercise(exerciseId), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getExercises() {
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }

    @PostMapping(path = "")
    @RolesAllowed(ERole.Name.ROLE_TRAINER)
    public ResponseEntity<?> addExercise(@RequestBody @NonNull Exercise exercise, Principal principal) {
        Exercise createdExercise;
        try {
            createdExercise = exerciseService.addExercise(exercise, principal.getName());
        } catch (Exception e) {
            System.out.println("Exception message: "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
        if (createdExercise == null) {
            return new ResponseEntity<>("Couldn't create exercise!", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(createdExercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable(value = "id") Long exerciseId ) {
        try {
            exerciseService.deleteExercise(exerciseId);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Error"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successfully deleted exercise with id=" + exerciseId, HttpStatus.OK);
    }

    @RequestMapping("/{id}/label-to-exercise-name/{labelId}")
    public ResponseEntity<?> addLabelToExerciseByLabelId(@PathVariable(value = "id") Long exerciseId, @PathVariable(value = "labelId") Long labelId) {
        try {
            exerciseService.addLabelToExerciseByLabelId(exerciseId, labelId);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Error"), HttpStatus.CONFLICT);
        }
        Exercise updatedExercise = exerciseService.getExercise(exerciseId);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @RequestMapping("/{id}/label-to-exercise-name/{labelName}")
    public ResponseEntity<?> addLabelToExerciseByLabelName(@PathVariable(value = "id") Long exerciseId, @PathVariable(value = "labelName") String labelName) {
        try {
            exerciseService.addLabelToExerciseByLabelName(exerciseId, labelName);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Error"), HttpStatus.CONFLICT);
        }
        Exercise updatedExercise = exerciseService.getExercise(exerciseId);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable(value = "id") Long exerciseId, @RequestBody @NonNull Exercise exercise) {
        exercise.setExerciseId(exerciseId);
        try {
            exerciseService.updateExercise(exercise);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdateExercise(@PathVariable(value = "id") Long exerciseId, @RequestBody Map<String, Object> fields) {
        // Sanitize and validate the data
        if (exerciseId <= 0 || fields == null || fields.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Invalid claim object received or invalid id or id does not match object
        }

        Exercise exercise = exerciseService.getExercise(exerciseId);

        // Does the object exist?
        if( exercise == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
        }

        // Remove id from request, we don't ever want to change the id.
        // This is not necessary, you can just do it to save time on the reflection
        // loop used below since we checked the id above
        fields.remove("id");
        try {
        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(Exercise.class, k); // find field in the object class
            field.setAccessible(true);
            if (field.getType() == EExerciseType.class)
                v = EExerciseType.valueOf((String) v);
            ReflectionUtils.setField(field, exercise, v); // set given field for defined object to value V
        });

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "ILLEGAL ARGUMENT ERROR"), HttpStatus.CONFLICT);
        } catch (Exception e ) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "OTHER ERROR"), HttpStatus.CONFLICT);
        }

        Exercise updated = exerciseService.partialUpdateExercise(exercise);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
