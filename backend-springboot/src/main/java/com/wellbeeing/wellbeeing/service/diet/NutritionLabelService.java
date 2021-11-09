package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface NutritionLabelService {
    List<NutritionLabel> getNutritionLabels();
    NutritionLabel getNutritionLabelById(UUID labelId) throws NotFoundException;
}
