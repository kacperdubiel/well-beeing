package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Role;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.repository.sport.SportLabelDAO;
import com.wellbeeing.wellbeeing.util.DataFromApi;
import javafx.scene.control.Pagination;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService, SportLabelService {
    private final ExerciseDAO exerciseDAO;
    private final SportLabelDAO sportLabelDAO;
    private final UserDAO userDAO;
    public ExerciseServiceImpl(@Qualifier("exerciseDAO") ExerciseDAO exerciseDAO,
                               @Qualifier("sportLabelDAO") SportLabelDAO sportLabelDAO,
                               @Qualifier("userDAO") UserDAO userDAO){
        this.exerciseDAO = exerciseDAO;
        this.sportLabelDAO = sportLabelDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Exercise addExercise(Exercise exercise, String creatorName) throws HttpMessageNotReadableException, NotFoundException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);
        System.out.println("Print id:" + user.getId());
        exercise.setCreator(user.getProfile());
        if (exerciseDAO.findAllByName(exercise.getName()).isEmpty()) {
            System.out.println("Pront name: "+exercise.getName());
            if (exercise.getName() == null || Objects.equals(exercise.getName(), ""))
            {
                throw new NotFoundException("Exercise must have a name!");
            }
            else if (exercise.getExerciseType() == null)
            {
                throw new NotFoundException("Exercise must have a type!");
            }
            exerciseDAO.save(exercise);
            return exercise;
        }
        throw new NotFoundException(String.format("Exercise with name=%s already exists", exercise.getName()));
    }

    @Override
    public boolean addLabelToExerciseByLabelId(long exercise_id, long label_id) throws NotFoundException {
        Exercise ex = exerciseDAO.findById(exercise_id).orElse(null);
        SportLabel label = sportLabelDAO.findById(label_id).orElse(null);
        if (ex == null) {
            throw new NotFoundException(String.format("There's no exercise with id=%d", exercise_id));
        }
        else if (label == null) {
            throw new NotFoundException(String.format("There's no label with id=%d", label_id));
        }
        ex.addLabelToExercise(label);
        label.addLabeledExercise(ex);
        exerciseDAO.save(ex);
        sportLabelDAO.save(label);
        return true;
    }

    @Override
    public SportLabel addSportLabel(SportLabel sportLabel, String creatorName) throws NotFoundException, ForbiddenException {
        User user = userDAO.findUserByEmail(creatorName).orElse(null);
        if (user == null)
            throw new NotFoundException("There's no user with email=" + creatorName);
        boolean hasPermission = user.getRoles().stream().map(Role::getRole).anyMatch(r -> r.equals(ERole.ROLE_ADMIN) || r.equals(ERole.ROLE_TRAINER));
        if(!hasPermission)
            throw new ForbiddenException("You have no permission to add Sport Labels!");
        try {
            sportLabelDAO.save(sportLabel);
            return sportLabel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteSportLabel(long sportLabelId, String userName) throws NotFoundException {
        return false;
    }

    @Override
    public SportLabel getSportLabelById(Long labelId) throws NotFoundException {
        SportLabel sportLabel = sportLabelDAO.findById(labelId).orElse(null);
        if(sportLabel != null)
            return sportLabel;
        throw new NotFoundException("Sport label with id: " + labelId + " not found");
    }

    @Override
    public boolean addLabelToExerciseByLabelName(long exercise_id, String label_name) throws NotFoundException {
        Exercise ex = exerciseDAO.findById(exercise_id).orElse(null);
        SportLabel label = sportLabelDAO.findSportLabelByName(label_name).orElse(null);
        if (ex == null) {
            throw new NotFoundException(String.format("There's no exercise with id=%d", exercise_id));
        }
        else if (label == null) {
            throw new NotFoundException(String.format("There's no label with name=%s", label_name));
        }
        ex.addLabelToExercise(label);
        label.addLabeledExercise(ex);
        exerciseDAO.save(ex);
        sportLabelDAO.save(label);
        return true;
    }

    @Override
    public boolean deleteExercise(long id) throws NotFoundException {
        if (exerciseDAO.findById(id).orElse(null) != null) {
            exerciseDAO.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format("There's no exercise with id=%s", id));
    }

    @Override
    public Exercise getExercise(long id) {
        return exerciseDAO.findById(id).orElse(null);
    }

    @Override
    public Exercise getExercise(long exerciseId, String userName) throws NotFoundException {
        Exercise foundExercise = exerciseDAO.findById(exerciseId).orElse(null);
        if (foundExercise == null)
            throw new NotFoundException("There's no exercise with id=" + userName);
        User user = userDAO.findUserByEmail(userName).orElse(null);
        if (user == null)
            throw new NotFoundException("There's no user with email=" + userName);
        double weight = 0;
        try {
            weight = user.getProfile().getProfileCard().getWeight();
        } catch (NullPointerException e) {
            System.out.println("User has no profile or profile card!");
        }
        foundExercise.setCaloriesBurned(foundExercise.countCaloriesPerHour(weight));
        return foundExercise;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDAO.findAll();
    }

    @Override
    public List<SportLabel> getAllSportLabels() {
        return sportLabelDAO.findAll(Sort.by("name"));
    }

    @Override
    public Page<Exercise> getAllExercises(Pageable pageable) {
        return exerciseDAO.findAll(pageable);
    }

    @Override
    public Page<Exercise> getAllExercisesFiltered(Specification<Exercise> exerciseSpec, Pageable pageable, String userName) throws NotFoundException {

        List<Exercise> exercises;

        Page<Exercise> pageExercises;
        pageExercises = exerciseDAO.findAll(exerciseSpec, pageable);
        exercises = pageExercises.getContent();
        Map<Long, Integer> calories = this.getCaloriesBurnedFromUser(exercises, userName);

        for (Exercise exercise:
                pageExercises.getContent()) {
            exercise.setCaloriesBurned(calories.get(exercise.getExerciseId()));
        }

        return pageExercises;
    }


    @Override
    public List<Exercise> getExercisesByType(EExerciseType type) {
        return exerciseDAO.findAllByExerciseType(type);
    }

    @Override
    public List<Exercise> getExercisesByCreatorId(UUID creator_id) {
        return exerciseDAO.findAllByCreator_Id(creator_id);
    }

    @Override
    public Exercise updateExercise(Exercise exercise) throws NotFoundException {
        // Not creating a new object
        Exercise targetExercise = exerciseDAO.findById(exercise.getExerciseId()).orElse(null);
        if(targetExercise == null)
            throw new NotFoundException(String.format("There's no exercise with id=%d", exercise.getExerciseId()));

        exerciseDAO.save(exercise);
        return exercise;
    }

    @Override
    public Exercise partialUpdateExercise(Exercise exercise) {
        exerciseDAO.save(exercise);
        return exercise;
    }

    @Override
    public void initiateExercises() {
        try {
            Map<String, Object> data = DataFromApi.getExercisesFromJsonWger();
            HashSet<String> categoriesLabels = (HashSet<String>) data.get("labels");
            List<Exercise> exercises = (List<Exercise>) data.get("exercises");
            List<Map<String, Object>> exercisesLabels = (List<Map<String, Object>>) data.get("exerciseLabels");
            categoriesLabels.forEach(l -> {
                addSportLabel(SportLabel.builder().name(l).build());
            });

            exercises.forEach(ex -> {
                if(exerciseDAO.findAllByName(ex.getName()).isEmpty())
                exerciseDAO.save(Exercise.builder().name(ex.getName()).instruction(ex.getInstruction()).exerciseType(EExerciseType.OTHER).build());
            });

            for (Map<String, Object> exercisesLabel : exercisesLabels) {
                Exercise ex = exerciseDAO.findExerciseByName(((Exercise) exercisesLabel.get("Exercise")).getName());
                try {
                    addLabelToExerciseByLabelName(ex.getExerciseId(), exercisesLabel.get("Label").toString());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException | IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<Long, Integer> getCaloriesBurnedFromUser(List<Exercise> exercises, String userName) throws NotFoundException {
        Map<Long, Integer> caloriesMap = new HashMap<>();
        User user = userDAO.findUserByEmail(userName).orElse(null);
        if (user == null)
            throw new NotFoundException("There's no user with email=" + userName);

        double weight  = user.getProfile().getProfileCard().getWeight();
        for (Exercise ex: exercises
             ) {
            caloriesMap.put(ex.getExerciseId(), ex.countCaloriesPerHour((int) weight));
        }
        System.out.println("User weight: " + weight);
        return caloriesMap;
    }

    @Override
    public SportLabel addSportLabel(SportLabel sportLabel) {
        if (!sportLabelDAO.findSportLabelByName(sportLabel.getName()).isPresent())
            return sportLabelDAO.save(sportLabel);
        else
            return null;
    }

    @Override
    public boolean deleteSportLabel(long id) {
        sportLabelDAO.deleteById(id);
        return  true;
    }

    @Override
    public SportLabel getSportLabel(long id) {
        return sportLabelDAO.findById(id).orElse(null);
    }

    @Override
    public SportLabel updateSportLabel(SportLabel sportLabel) {
        return sportLabelDAO.save(sportLabel);
    }
}
