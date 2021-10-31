package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.diet.NutritionLabelDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("nutritionLabelService")
public class NutritionLabelServiceImpl implements NutritionLabelService {
    private NutritionLabelDAO nutritionLabelDAO;

    public NutritionLabelServiceImpl(@Qualifier("nutritionLabelDAO") NutritionLabelDAO nutritionLabelDAO){
        this.nutritionLabelDAO = nutritionLabelDAO;
    }
    @Override
    public List<NutritionLabel> getNutritionLabels() {
        return nutritionLabelDAO.findAll();
    }

    @Override
    public NutritionLabel getNutritionLabelById(UUID labelId) throws NotFoundException {
        NutritionLabel nutritionLabel = nutritionLabelDAO.findById(labelId).orElse(null);
        if(nutritionLabel != null)
            return nutritionLabel;
        throw new NotFoundException("Nutrition label with id: " + labelId + " not found");
    }
}
