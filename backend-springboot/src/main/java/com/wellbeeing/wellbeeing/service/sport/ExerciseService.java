package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ExerciseService {
    Exercise addExercise(Exercise exercise, String creatorName);
    boolean addLabelToExerciseByLabelId(long exerciseId, long labelId);
    boolean addLabelToExerciseByLabelName(long exerciseId, String labelName);
    boolean deleteExercise(long exerciseId);
    Exercise getExercise(long exerciseId);
    List<Exercise> getAllExercises();
    List<Exercise> getExercisesByType(EExerciseType type);
    List<Exercise> getExercisesByCreatorId(UUID creatorId);
    Exercise updateExercise(Exercise exercise);
    Exercise partialUpdateExercise(Exercise exercise);
}
