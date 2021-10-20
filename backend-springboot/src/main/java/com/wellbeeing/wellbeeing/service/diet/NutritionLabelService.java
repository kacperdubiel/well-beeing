package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;

import java.util.List;
import java.util.UUID;

public interface NutritionLabelService {
    List<NutritionLabel> getNutritionLabelsByDishId(UUID dishId);
    List<NutritionLabel> getNutritionLabelsByAilmentId(UUID dishId);
    List<NutritionLabel> getNutritionLabelsByDietId(UUID dishId);
}
