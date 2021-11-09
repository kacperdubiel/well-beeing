package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.service.diet.NutritionLabelService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NutritionLabelController {
    private NutritionLabelService nutritionLabelService;

    public NutritionLabelController(@Qualifier("nutritionLabelService") NutritionLabelService nutritionLabelService){
        this.nutritionLabelService = nutritionLabelService;
    }

    @RequestMapping(path = "/nutrition-label/{labelId}", method = RequestMethod.GET)
    public ResponseEntity<?> getLabelById(@PathVariable("labelId") UUID labelID) throws NotFoundException {
        NutritionLabel nutritionLabel = nutritionLabelService.getNutritionLabelById(labelID);
        return new ResponseEntity<>(nutritionLabel, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-label", method = RequestMethod.GET)
    public ResponseEntity<?> getAllNutritionLabels() {
        return new ResponseEntity<>(nutritionLabelService.getNutritionLabels(), HttpStatus.OK);
    }
}
