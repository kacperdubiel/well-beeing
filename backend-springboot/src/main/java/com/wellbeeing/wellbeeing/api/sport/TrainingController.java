package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.sport.AddExerciseToTrainingRequest;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPosition;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.service.sport.ExerciseService;
import com.wellbeeing.wellbeeing.service.sport.TrainingService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> getTrainings(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "20") int size,
                                          Principal principal) {
//        return new ResponseEntity<>(trainingService.getAllTrainings(), HttpStatus.OK);
        try {
            List<Training> trainings;
            Pageable paging = PageRequest.of(page, size);

            Page<Training> pageTrainings;
            pageTrainings = trainingService.getAllTrainings(paging, principal.getName());
            trainings = pageTrainings.getContent();
            PaginatedResponse response = PaginatedResponse.builder()
                    .objects(trainings)
                    .currentPage(pageTrainings.getNumber())
                    .totalItems(pageTrainings.getTotalElements())
                    .totalPages(pageTrainings.getTotalPages()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
