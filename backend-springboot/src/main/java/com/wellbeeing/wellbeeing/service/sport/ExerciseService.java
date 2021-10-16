package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.repository.sport.SportLabelDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.UUID;
@Service("exerciseService")
public class ExerciseService implements ExerciseServiceApi {
    private ExerciseDAO exerciseDAO;
    private SportLabelDAO sportLabelDAO;
    public ExerciseService(@Qualifier("exerciseDAO") ExerciseDAO exerciseDAO, @Qualifier("sportLabelDAO") SportLabelDAO sportLabelDAO){
        this.exerciseDAO = exerciseDAO;
        this.sportLabelDAO = sportLabelDAO;
    }

    @Override
    public Exercise addExercise(Exercise exercise) {
        if (exerciseDAO.findAllByName(exercise.getName()).isEmpty()) {
            exerciseDAO.save(exercise);
            return exercise;
        }
        return null;
    }

    @Override
    public boolean addLabelToExercise(long exercise_id, long label_id) {
        Exercise ex = exerciseDAO.findById(exercise_id).orElse(null);
        SportLabel label = sportLabelDAO.findById(label_id).orElse(null);
        if (ex != null && label != null) {
            ex.addLabelToExercise(label);
            label.addLabeledExercise(ex);
            exerciseDAO.save(ex);
            sportLabelDAO.save(label);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExercise(long id) {
        if (exerciseDAO.findById(id).orElse(null) != null) {
            exerciseDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Exercise getExercise(long id) {
        return exerciseDAO.findById(id).orElse(null);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDAO.findAll();
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
    public Exercise updateExercise(Exercise exercise) {
        // Not creating a new object
        if(exerciseDAO.findById(exercise.getExercise_id()).orElse(null) != null)
            return null;
        exerciseDAO.save(exercise);
        return exercise;
    }
}
