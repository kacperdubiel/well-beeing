package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.message.diet.CsvImportResponse;
import com.wellbeeing.wellbeeing.service.diet.CsvImportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CsvImportController {

    private final CsvImportService csvImportService;

    public CsvImportController(@Qualifier("csvImportService") CsvImportService csvImportService){
        this.csvImportService = csvImportService;
    }

    @RequestMapping(path = "/import/products", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importProducts(@RequestParam("file") MultipartFile file) {
       ArrayList<String> result = csvImportService.importProducts(file);
       CsvImportResponse response = new CsvImportResponse(result.size(), result);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/import/ailments", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importAilments(@RequestParam("file") MultipartFile file) {
        ArrayList<String> result = csvImportService.importAilments(file);
        CsvImportResponse response = new CsvImportResponse(result.size(), result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/import/diets", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importDiets(@RequestParam("file") MultipartFile file) {
        ArrayList<String> result = csvImportService.importDiets(file);
        CsvImportResponse response = new CsvImportResponse(result.size(), result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
