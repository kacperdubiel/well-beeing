package com.wellbeeing.wellbeeing.api.diet.message;

import lombok.Data;

import java.util.UUID;

@Data
public class GenerateNutritionPlanRequest {
    private UUID dietId;
    private UUID nutritionPlanId;
}
