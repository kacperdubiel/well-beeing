package com.wellbeeing.wellbeeing.api.sport;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.api.diet.message.CsvImportResponse;
import com.wellbeeing.wellbeeing.service.files.FileService;
import com.wellbeeing.wellbeeing.service.sport.ExerciseService;
import com.wellbeeing.wellbeeing.service.sport.SportImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/sport/import")
@RestController
public class SportImportController {
    private final FileService fileService;
    private final ExerciseService exerciseService;
    private final SportImportService sportImportService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public SportImportController(
            @Qualifier("fileService") FileService fileService,
            @Qualifier("exerciseService") ExerciseService exerciseService,
            @Qualifier("sportImportService") SportImportService sportImportService) {
        this.fileService = fileService;
        this.exerciseService = exerciseService;
        this.sportImportService = sportImportService;
    }

    @RequestMapping(path = "/exercises", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importExercises(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        Map<String, Object> result = sportImportService.importExercises(file);
//        CsvImportResponse response = new CsvImportResponse(result.size(), result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/trainings", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importTrainings(@RequestParam("file") MultipartFile file) {
        ArrayList<String> result = sportImportService.importTrainings(file);
        CsvImportResponse response = new CsvImportResponse(result.size(), result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
