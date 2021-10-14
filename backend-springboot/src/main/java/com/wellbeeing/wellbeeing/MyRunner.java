package com.wellbeeing.wellbeeing;

import com.wellbeeing.wellbeeing.domain.sport.ETrainingDifficulty;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import com.wellbeeing.wellbeeing.domain.sport.Training;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.repository.sport.TrainingDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class MyRunner implements CommandLineRunner {


    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
    @Autowired
    @Qualifier("exerciseDAO")
    private ExerciseDAO exerciseDAO;
    @Autowired
    @Qualifier("trainingDAO")
    private TrainingDAO trainingDAO;

    @Override
    public void run(String... args) throws Exception {
        Training training_a = new Training("Training_A", ETrainingDifficulty.MEDIUM);
        Training training_b = new Training("Training_B", ETrainingDifficulty.HARD);
        trainingDAO.saveAll(Arrays.asList(training_a, training_b));

        exerciseDAO.save(new Exercise("Exercise_1", new ExerciseInTraining(training_a, 15,20 )));
        exerciseDAO.save(new Exercise("Exercise_2", new ExerciseInTraining(training_b, 20,30 )));
    }
}
