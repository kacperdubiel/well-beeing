package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.account.DieticianProfileDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.diet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;

@Service("dishService")
public class DishServiceImpl implements DishService {
    @Autowired
    private EntityManager entityManager;
    private DishDAO dishDAO;
    private ProfileDAO profileDAO;
    private NutritionLabelDAO nutritionLabelDAO;
    private DishProductDetailDAO dishProductDetailDAO;
    private DieticianProfileDAO dieticianDAO;
    private DishMealTypeDAO dishMealTypeDAO;
    private ElementDetailService elementDetailService;

    @Autowired
    public DishServiceImpl(@Qualifier("dishDAO") DishDAO dishDAO,
                           @Qualifier("nutritionLabelDAO") NutritionLabelDAO nutritionLabelDAO,
                           @Qualifier("dishProductDetailDAO") DishProductDetailDAO dishProductDetailDAO,
                           @Qualifier("dishMealTypeDAO") DishMealTypeDAO dishMealTypeDAO,
                           @Qualifier("profileDAO") ProfileDAO profileDAO,
                           @Qualifier("elementDetailService") ElementDetailService elementDetailService,
                           @Qualifier("dieticianProfileDAO") DieticianProfileDAO dieticianDAO) {
        this.dishDAO = dishDAO;
        this.nutritionLabelDAO = nutritionLabelDAO;
        this.dishProductDetailDAO = dishProductDetailDAO;
        this.dishMealTypeDAO = dishMealTypeDAO;
        this.profileDAO = profileDAO;
        this.elementDetailService = elementDetailService;
        this.dieticianDAO = dieticianDAO;
    }

    @Override
    public Dish getDishById(UUID dishId) throws NotFoundException {
        Dish dish = dishDAO.findById(dishId).orElse(null);
        if(dish != null)
            return dish;
        throw new NotFoundException("Dish with id" + dishId + " not found");
    }

    @Override
    public Page<Dish> getAllDishes(int numberOfElements, int page) {
        return dishDAO.findAll(PageRequest.of(page, numberOfElements, Sort.by("name")));
    }


    @Override
    public Page<Dish> getDishesWithNameLike(String namePart, int numberOfElements, int page) {
        return dishDAO.findActivePublishedByNameLikeIgnoreCase(namePart,
                PageRequest.of(page, numberOfElements, Sort.by("name")));
    }

    @Override
    public boolean updateCaloriesAndMacrosByDishId(UUID dishId) throws NotFoundException {
        Dish dish = getDishById(dishId);
        return updateCaloriesAndMacrosByDish(dish);
    }

    private boolean updateCaloriesAndMacrosByDish(Dish dish){
        dish.setDerived();
        dishDAO.save(dish);
        return true;
    }

    @Override
    public Dish addDish(Dish dish, UUID creatorId, boolean nameCheck) throws NotFoundException, ConflictException {
        if(nameCheck)
            checkName(dish);
        dish.setDerivedNutritionalValues(new NutritionalValueDerivedData());
        dish.setCreatedDate(LocalDate.now());
        dish.setDishCreator(dieticianDAO.findDieticianProfileById(creatorId).orElse(null));
        Dish actDish = dishDAO.save(dish);
        for(DishMealType d : actDish.getDishMealTypes()){
            d.setDish(actDish);
            dishMealTypeDAO.saveAndFlush(d);
        }
        for(DishProductDetail d : actDish.getDishProductDetails()){
            d.setDish(actDish);
            d.setDerivedNutritionalValues(new NutritionalValueDerivedData());
            DishProductDetail dpd = dishProductDetailDAO.saveAndFlush(d);
            entityManager.clear();
            DishProductDetail newDetail = dishProductDetailDAO.findById(dpd.getId()).orElse(null);
            if(newDetail != null){
                newDetail.setDerived();
                dishProductDetailDAO.save(newDetail);
            }
        }
        Dish dishNotUpdated = getDishById(actDish.getId());
        updateCaloriesAndMacrosByDish(dishNotUpdated);
        return dishDAO.save(dishNotUpdated);
    }

    @Override
    public Dish updateDish(Dish dish, UUID dishId) throws NotFoundException, ConflictException {
        Dish dishToUpdate = getDishById(dishId);
        if(!dish.getName().equals(dishToUpdate.getName()))
            checkName(dish);
        dishToUpdate.getDishProductDetails().forEach(d -> dishProductDetailDAO.deleteById(d.getId()));
        dishToUpdate.getDishMealTypes().forEach(d -> dishMealTypeDAO.deleteById(d.getId()));
        dishDAO.save(dishToUpdate);

        dish.setId(dishToUpdate.getId());
        return addDish(dish, dishToUpdate.getDishCreator().getId(), false);
    }

    private void checkName(Dish dish) throws ConflictException {
        if(dishDAO.findAllByName(dish.getName()).size() > 0)
            throw new ConflictException("Dish with name + " + dish.getName() + " already exists");
    }

    @Override
    public boolean deleteDish(UUID dishId) throws NotFoundException {
        Dish dish = getDishById(dishId);
        dish.setActive(false);
        dishDAO.save(dish);
        //dishDAO.deleteById(dishId);
        return true;
    }

    @Override
    public Page<Dish> getLabeledDishes(List<UUID> labelIds, int numberOfElements, int page, String namePart) {
        if (labelIds.isEmpty()){
            return getDishesWithNameLike(namePart, numberOfElements, page);
        }
        List<Dish> allDishes = dishDAO.findAll();
        List<UUID> resultDishes = new ArrayList<>();
        for(Dish dish : allDishes){
            List <NutritionLabel> dishLabels = dish.getAllowedForNutritionLabels();
            List <UUID> dishLabelsIds = new ArrayList<>();
            dishLabels.forEach(l -> dishLabelsIds.add(l.getNutritionLabelId()));
            if(dishLabelsIds.containsAll(labelIds)){
                resultDishes.add(dish.getId());
            }
        }
        return dishDAO.findActivePublishedByDishIds(resultDishes, namePart, PageRequest.of(page, numberOfElements, Sort.by("name")));
    }

    @Override
    public Page<Dish> getDieticianDishesByDieticianId(UUID dieticianId, int page, int size) {
        return dishDAO.findByDishCreatorIdAndActive(dieticianId, true, PageRequest.of(page, size, Sort.by(Sort.Order.asc("name"),
                Sort.Order.desc("createdDate"))));
    }

    @Override
    public Dish updateDishImg(UUID dishId, String fileName) throws NotFoundException {
        Dish actDish = getDishById(dishId);
        actDish.setImgDishPath(fileName);
        return dishDAO.save(actDish);
    }

    @Override
    public Dish changePublishedState(UUID dishId, boolean draft) throws NotFoundException {
        Dish dish = getDishById(dishId);
        dish.setDraft(draft);
        return dishDAO.save(dish);
    }

    @Override
    public Map<String, Map<String, Double>> countDetailedElementsAmountsByDishId(UUID dishId) throws NotFoundException {
        Map<String, Map<String, Double>> result = new HashMap<>();
        Dish dish = getDishById(dishId);

        List<DishProductDetail> dishProdDetails = dish.getDishProductDetails();

        Map<String, Double> reportMacroDetails = new HashMap<>();
        Map<String, Double> reportVitaminDetails = new HashMap<>();
        Map<String, Double> reportMineralDetails = new HashMap<>();

        elementDetailService.countProdDetails(dishProdDetails, reportMacroDetails, reportVitaminDetails, reportMineralDetails);

        result.put("macroDetailsGram", reportMacroDetails);
        result.put("vitaminsMicroGram", reportVitaminDetails);
        result.put("mineralsMilliGram", reportMineralDetails);
        return result;
    }
}
