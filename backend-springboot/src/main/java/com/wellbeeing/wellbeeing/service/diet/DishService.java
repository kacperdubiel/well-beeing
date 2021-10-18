package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;

import java.util.List;
import java.util.UUID;

public interface DishService {
    Dish getDishById(UUID dishId);
    List<Dish> getAllDishes();
}
