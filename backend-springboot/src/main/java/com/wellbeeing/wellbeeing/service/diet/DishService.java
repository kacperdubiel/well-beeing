package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;

import javassist.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DishService {
    Dish getDishById(UUID dishId) throws NotFoundException;
    List<Dish> getAllDishes(int numberOfElements, int page);
    List<Dish> getDishesWithNameLike(String namePart, int numberOfElements, int page);
    boolean updateCaloriesAndMacrosByDishId(UUID dishId) throws NotFoundException;
    Map<String, Map<String, Double>> countDishDetailsByDishId(UUID dishId) throws NotFoundException;
}
