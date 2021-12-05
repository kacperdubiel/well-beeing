package com.wellbeeing.wellbeeing.service.sport;

import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import com.wellbeeing.wellbeeing.repository.sport.ExerciseDAO;
import com.wellbeeing.wellbeeing.service.files.FileService;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service("sportImportService")
public class SportImportServiceImpl implements SportImportService {
    private final FileService fileService;
    private final ExerciseService exerciseService;
    private final TrainingService trainingService;
    private final ExerciseDAO exerciseDAO;
    @Value("/uploads")
    private String uploadPath;

    public SportImportServiceImpl(FileService fileService,
                                  @Qualifier("exerciseService") ExerciseService exerciseService,
                                  @Qualifier("trainingService") TrainingService trainingService,
                                  @Qualifier("exerciseDAO") ExerciseDAO exerciseDAO) {
        this.fileService = fileService;
        this.exerciseService = exerciseService;
        this.trainingService = trainingService;
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public Map<String, Object> importExercises(MultipartFile exercises) throws FileNotFoundException {
        String exercisesPath = fileService.save(exercises);
        Path file = Paths.get(uploadPath)
                .resolve(exercisesPath);
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(String.valueOf(file)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;

        // A JSON array. JSONObject supports java.util.List interface.
        org.json.simple.JSONArray exercisesJson = (org.json.simple.JSONArray) jsonObject.get("exercises");
        Set<String> categories = new HashSet<>();
        // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
        // Iterators differ from enumerations in two ways:
        // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
        // 2. Method names have been improved.
        List<Map<String, Object>> resultMap = new ArrayList<>();
        List<Exercise> addExercises = new ArrayList<>();
        for (org.json.simple.JSONObject object : (Iterable<org.json.simple.JSONObject>) exercisesJson) {
            String name = object.get("name").toString();
            String description = object.get("description").toString();
            String instruction = object.get("instruction").toString();
            String exerciseType = object.get("exerciseType").toString();
            String met = object.get("met").toString(); // double
            Double metDouble = BigDecimal.valueOf(Float.parseFloat(met))
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();
            JSONArray labels = (JSONArray) object.get("labels");
            List<String> labelsNames = new ArrayList<>();

            Exercise ex = Exercise.builder()
                    .name(name)
                    .description(description)
                    .instruction(instruction)
                    .met(metDouble)
                    .exerciseType(EExerciseType.valueOf(exerciseType))
                    .isDeleted(false)
                    .build();
            if (labels != null) {
                int len = labels.size();
                for (int i = 0; i < len; i++) {
                    labelsNames.add(labels.get(i).toString());
                }
            }

            resultMap.add(new HashMap<String, Object>() {{
                put("Exercise", ex);
                put("Labels", labelsNames);
            }});
            addExercises.add(ex);
            categories.addAll(labelsNames);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("exercises", addExercises);
        result.put("labels", categories);
        result.put("exerciseLabels", resultMap);


        categories.forEach(l -> {
            exerciseService.addSportLabel(SportLabel.builder().name(l).build());
        });
        addExercises.forEach(ex -> {
            try {
                exerciseService.addExercise(ex, "None");
            } catch (NotFoundException | ConflictException e) {
                e.printStackTrace();
            }
        });

        for (Map<String, Object> exercisesLabel : resultMap) {
            Exercise ex = exerciseDAO.findExerciseByName(((Exercise) exercisesLabel.get("Exercise")).getName());
            ((List<String>) exercisesLabel.get("Labels")).forEach(label -> {
                        try {
                            exerciseService.addLabelToExerciseByLabelName(ex.getExerciseId(), label);
                        } catch (NotFoundException exc) {
                            exc.printStackTrace();
                        }
                    }
            );

        }

        return result;
    }

    @Override
    public ArrayList<String> importTrainings(MultipartFile trainings) {
        return null;
    }
}
