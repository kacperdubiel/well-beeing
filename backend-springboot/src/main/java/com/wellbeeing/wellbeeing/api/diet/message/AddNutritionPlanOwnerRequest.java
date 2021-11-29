package com.wellbeeing.wellbeeing.api.diet.message;

import lombok.Data;

import java.util.UUID;

@Data
public class AddNutritionPlanOwnerRequest {
    UUID nutritionPlanOwnerId;
}
