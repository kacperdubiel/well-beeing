package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseServiceApi {
    Exercise addExercise(Exercise exercise);
    boolean addLabelToExercise(long exercise_id, long label_id);
    boolean deleteExercise(long id);
    Exercise getExercise(long id);
    List<Exercise> getAllExercises();
    List<Exercise> getExercisesByType(EExerciseType type);
    List<Exercise> getExercisesByCreatorId(UUID creator_id);
    Exercise updateExercise(Exercise exercise);
}
