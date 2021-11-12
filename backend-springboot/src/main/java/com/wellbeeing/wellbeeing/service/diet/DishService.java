package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DishService {
    Dish getDishById(UUID dishId) throws NotFoundException;
    Page<Dish> getAllDishes(int numberOfElements, int page);
    Page<Dish> getDishesWithNameLike(String namePart, int numberOfElements, int page);
    boolean updateCaloriesAndMacrosByDishId(UUID dishId) throws NotFoundException;
    Dish addDish(Dish dish, UUID creatorId, boolean nameCheck) throws NotFoundException, ConflictException;
    Dish updateDish(Dish dish, UUID dishId) throws NotFoundException, ConflictException;
    boolean deleteDish(UUID dishId) throws NotFoundException;
    Page<Dish> getLabeledDishes(List<UUID> labelIds, int numberOfElements, int page, String namePart);
    List<Dish> getDieticianDishesByDieticianId(UUID dieticianId);
    Dish updateDishImg(UUID dishId, String fileName) throws NotFoundException;
    Dish changePublishedState(UUID dishId, boolean draft) throws NotFoundException;
}
