package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ExerciseService {
    Exercise addExercise(Exercise exercise, String creatorName) throws NotFoundException;

    boolean deleteExercise(long exerciseId) throws NotFoundException;

    Exercise getExercise(long exerciseId);

    Exercise getExercise(long exerciseId, String userName) throws NotFoundException;

    List<Exercise> getAllExercises();

    Page<Exercise> getAllExercises(Pageable pageable);

    Page<Exercise> getAllExercisesFiltered(Specification<Exercise> exerciseSpec,
                                           Pageable pageable, String userName) throws NotFoundException;

    List<Exercise> getExercisesByType(EExerciseType type);

    List<Exercise> getExercisesByCreatorId(UUID creatorId);

    Exercise updateExercise(Exercise exercise) throws NotFoundException;

    Exercise partialUpdateExercise(Exercise exercise);

    void initiateExercises();

    // LABELS
    List<SportLabel> getAllSportLabels();

    SportLabel getSportLabelById(Long labelId) throws NotFoundException;

    boolean addLabelToExerciseByLabelId(long exerciseId, long labelId) throws NotFoundException;

    SportLabel addSportLabel(SportLabel sportLabel, String creatorName) throws NotFoundException, ForbiddenException;

    SportLabel addSportLabelWithAilments(SportLabel sportLabel, ArrayList<Map<String, Object>> ailments) throws NotFoundException;

    boolean deleteSportLabel(long sportLabelId, String userName) throws NotFoundException;

    boolean addLabelToExerciseByLabelName(long exerciseId, String labelName) throws NotFoundException;

    Map<Long, Integer> getCaloriesBurnedFromUser(List<Exercise> exercises, String userName) throws NotFoundException;
}
