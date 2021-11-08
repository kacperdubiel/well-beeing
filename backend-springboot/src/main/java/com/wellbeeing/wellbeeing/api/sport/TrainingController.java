package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.sport.AddExerciseToTrainingRequest;
import com.wellbeeing.wellbeeing.domain.message.sport.ChangeTrainingPlanRequestStatusRequest;
import com.wellbeeing.wellbeeing.domain.sport.*;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.ExerciseService;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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
            @Join(path = "exerciseInTrainings", alias = "extr")
            @Join(path = "extr.exercise", alias = "ex")
            @Join(path = "ex.labels", alias = "ls")
            @And({
                @Spec(path = "trainingDifficulty", spec = Equal.class),
                @Spec(path = "name", spec = LikeIgnoreCase.class),
                @Spec(path = "totalTrainingTimeSeconds", params = {"secondsMoreThan","secondsLessThan"},spec = Between.class),
                @Spec(path = "ex.name", params="exerciseName", spec = LikeIgnoreCase.class),
                @Spec(path = "ls.name", params="label", spec = LikeIgnoreCase.class)
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
    public ResponseEntity<?> deleteTraining(@PathVariable(value = "id") Long trainingId ) {
        try {
            trainingService.deleteTraining(trainingId);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), "Error"), HttpStatus.CONFLICT);
        }
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
            return new ResponseEntity<>(new ErrorMessage(String.format("Couldn't add exercise %d to training with id=%d",exerciseId, trainingId), "Error"), HttpStatus.OK);
        }
        return new ResponseEntity<>(addedExercise, HttpStatus.OK);
    }
    @GetMapping("/calories-burned/{trainingId}")
    public ResponseEntity<?> getCaloriesBurnedFromClient(@PathVariable(value = "trainingId") long trainingId, Principal principal) throws NotFoundException {
        double caloriesBurned;
            caloriesBurned = trainingService.getCaloriesBurnedFromUser(trainingId, principal.getName());
        if (caloriesBurned == -1) {
            return new ResponseEntity<>(new ErrorMessage("",""), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(caloriesBurned, HttpStatus.OK);
    }

}
