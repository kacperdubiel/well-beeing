package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.service.diet.CsvImportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;

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
       boolean result = csvImportService.importProducts(file);
       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/import/ailments", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importAilments(@RequestParam("file") MultipartFile file) {
        boolean result = csvImportService.importAilments(file);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/import/diets", method = RequestMethod.POST)
    @RolesAllowed(ERole.Name.ROLE_ADMIN)
    public ResponseEntity<?> importDiets(@RequestParam("file") MultipartFile file) {
        boolean result = csvImportService.importDiets(file);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
