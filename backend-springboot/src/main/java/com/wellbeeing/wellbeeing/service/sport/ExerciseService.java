package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import javafx.scene.control.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ExerciseService {
    Exercise addExercise(Exercise exercise, String creatorName) throws NotFoundException;
    boolean addLabelToExerciseByLabelId(long exerciseId, long labelId) throws NotFoundException;
    boolean addLabelToExerciseByLabelName(long exerciseId, String labelName) throws NotFoundException;
    boolean deleteExercise(long exerciseId) throws NotFoundException;
    Exercise getExercise(long exerciseId);
    List<Exercise> getAllExercises();

    Page<Exercise> getAllExercises(Pageable pageable);

    List<Exercise> getExercisesByType(EExerciseType type);
    List<Exercise> getExercisesByCreatorId(UUID creatorId);
    Exercise updateExercise(Exercise exercise) throws NotFoundException;
    Exercise partialUpdateExercise(Exercise exercise);
}
