package com.wellbeeing.wellbeeing.domain.message.diet;

import lombok.Data;

import java.util.UUID;

@Data
public class GenerateNutritionPlanRequest {
    private UUID dietId;
    private UUID nutritionPlanId;
}
