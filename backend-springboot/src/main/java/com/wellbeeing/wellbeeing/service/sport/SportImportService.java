package com.wellbeeing.wellbeeing.service.sport;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public interface SportImportService {
    Map<String, Object> importExercises(MultipartFile exercises) throws FileNotFoundException;

    ArrayList<String> importTrainings(MultipartFile trainings);
}
