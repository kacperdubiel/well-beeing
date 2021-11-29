package com.wellbeeing.wellbeeing.api.diet.api;

import com.wellbeeing.wellbeeing.service.diet.DietService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DietController {
    private final DietService dietService;

    public DietController(@Qualifier("dietService") DietService dietService){
        this.dietService = dietService;
    }

    @RequestMapping(path = "/diet", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAilments() {
        return new ResponseEntity<>(dietService.getAllDiets(), HttpStatus.OK);
    }
}
