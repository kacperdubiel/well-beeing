package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.diet.DishService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DishController {
    public DishService dishService;

    @Autowired
    public DishController(@Qualifier("dishService") DishService dishService){
        this.dishService = dishService;
    }

    @RequestMapping(path = "/dish/{dishId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDishById(@PathVariable("dishId") UUID dishID) throws NotFoundException {
        Dish actDish = dishService.getDishById(dishID);
        return new ResponseEntity<>(actDish, HttpStatus.OK);
    }

    @RequestMapping(path = "/dish/{dishId}/update-derived", method = RequestMethod.GET)
    public ResponseEntity<?> updateDishDerivedById(@PathVariable("dishId") UUID dishID) throws NotFoundException {
        boolean resp = dishService.updateCaloriesAndMacrosByDishId(dishID);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(path = "/dish", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDishesPaginated(@RequestParam(value = "page", defaultValue = "0") String page,
                                                     @RequestParam(value = "size", defaultValue = "10") String size){
        Page<Dish> dishesPage = dishService.getAllDishes(Integer.parseInt(size), Integer.parseInt(page));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(dishesPage.getNumber())
                .totalItems(dishesPage.getTotalElements())
                .totalPages(dishesPage.getTotalPages())
                .objects(dishesPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/dish/search", method = RequestMethod.GET)
    public ResponseEntity<?> getDishesWithNameLike(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                     @RequestParam(value = "nameLike", defaultValue = "a") String nameLike){
        Page<Dish> dishesPage = dishService.getDishesWithNameLike(nameLike, size, page);
        PaginatedResponse response = PaginatedResponse.builder()
                    .currentPage(dishesPage.getNumber())
                    .totalItems(dishesPage.getTotalElements())
                    .totalPages(dishesPage.getTotalPages())
                    .objects(dishesPage.getContent())
                    .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
