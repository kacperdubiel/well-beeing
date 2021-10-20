package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.account.User;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.repository.account.UserDAO;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.repository.sport.SportLabelDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Service("exerciseService")
public class ExerciseServiceImpl implements ExerciseService {
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
}
