package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface NutritionLabelService {
    List<NutritionLabel> getNutritionLabels();
    NutritionLabel getNutritionLabelById(UUID labelId) throws NotFoundException;
    NutritionLabel addNutritionLabel(NutritionLabel label) throws ConflictException;
    NutritionLabel updateNutritionLabel(NutritionLabel label, UUID nutritionLabelId) throws NotFoundException, ConflictException;
    NutritionLabel deleteNutritionLabel(UUID nutritionLabelId) throws NotFoundException;
    Page<NutritionLabel> getActiveNutritionLabelsByCreatorId(UUID creatorId, int page, int size);
}
