package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DishController {
    public DishService dishService;
    public UserService userService;

    @Autowired
    public DishController(@Qualifier("dishService") DishService dishService,
                          @Qualifier("userService") UserService userService){
        this.dishService = dishService;
        this.userService = userService;
    }

    @RequestMapping(path = "/dish/{dishId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDishById(@PathVariable("dishId") UUID dishID) throws NotFoundException {
        Dish actDish = dishService.getDishById(dishID);
        return new ResponseEntity<>(actDish, HttpStatus.OK);
    }

    //@RolesAllowed(ERole.Name.ROLE_ADMIN)
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
                                                     @RequestParam(value = "nameLike", defaultValue = "") String nameLike){
        Page<Dish> dishesPage = dishService.getDishesWithNameLike(nameLike, size, page);
        PaginatedResponse response = PaginatedResponse.builder()
                    .currentPage(dishesPage.getNumber())
                    .totalItems(dishesPage.getTotalElements())
                    .totalPages(dishesPage.getTotalPages())
                    .objects(dishesPage.getContent())
                    .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@RolesAllowed(ERole.Name.ROLE_DIETICIAN)
    @RequestMapping(path = "/dish/dietician", method = RequestMethod.POST)
    public ResponseEntity<?> addDish(Principal principal, @RequestBody @NonNull Dish dish) throws NotFoundException {
        return new ResponseEntity<>(dishService.addDish(dish, userService.findUserIdByUsername(principal.getName())), HttpStatus.OK);
    }

    //@RolesAllowed(ERole.Name.ROLE_DIETICIAN)
    @RequestMapping(path = "/dish/{dishId}/dietician", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDish(Principal principal, @RequestBody @NonNull Dish dish, @PathVariable("dishId") UUID dishId) throws NotFoundException, ForbiddenException {
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        Dish actDish = dishService.getDishById(dishId);
        if(!actDish.getDishCreator().getId().equals(dieticianId))
            throw new ForbiddenException("Access to dish with id: " + dishId + "forbidden");
        return new ResponseEntity<>(dishService.updateDish(dish, dishId), HttpStatus.OK);
    }

    //@RolesAllowed(ERole.Name.ROLE_DIETICIAN)
    @RequestMapping(path = "/dish/dietician", method = RequestMethod.GET)
    public ResponseEntity<?> getDieticianDishes(Principal principal){
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        return new ResponseEntity<>(dishService.getDieticianDishesByDieticianId(dieticianId), HttpStatus.OK);
    }

    //@RolesAllowed(ERole.Name.ROLE_DIETICIAN)
    @RequestMapping(path = "/dish/{dishId}/dietician", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDish(Principal principal, @PathVariable("dishId") UUID dishId) throws ForbiddenException, NotFoundException {
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        Dish actDish = dishService.getDishById(dishId);
        if(!actDish.getDishCreator().getId().equals(dieticianId))
            throw new ForbiddenException("Access to dish with id: " + dishId + "forbidden");
        return new ResponseEntity<>(dishService.deleteDish(dishId), HttpStatus.OK);
    }

    @RequestMapping(path = "/dish/labeled", method = RequestMethod.POST)
    public ResponseEntity<?> getLabeledDishes(@RequestBody @NonNull List<UUID> labelsIds,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size,
                                              @RequestParam(value = "nameLike", defaultValue = "") String nameLike)  {
        Page<Dish> dishesPage = dishService.getLabeledDishes(labelsIds, size, page, nameLike);
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(dishesPage.getNumber())
                .totalItems(dishesPage.getTotalElements())
                .totalPages(dishesPage.getTotalPages())
                .objects(dishesPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
