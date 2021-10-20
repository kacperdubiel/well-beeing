package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.type.EDetailedMacro;
import com.wellbeeing.wellbeeing.domain.diet.type.EMineral;
import com.wellbeeing.wellbeeing.domain.diet.type.EVitamin;
import com.wellbeeing.wellbeeing.repository.diet.DishDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("dishService")
public class DishServiceImpl implements DishService {
    private DishDAO dishDAO;

    @Autowired
    public DishServiceImpl(@Qualifier("dishDAO") DishDAO dishDAO){
        this.dishDAO = dishDAO;
    }

    @Override
    public Dish getDishById(UUID dishId) throws NotFoundException {
        Dish dish = dishDAO.findById(dishId).orElse(null);
        if(dish != null)
            return dish;
        throw new NotFoundException("NotFoundException");
    }

    @Override
    public List<Dish> getAllDishes(int numberOfElements, int startIndex) {
        return dishDAO.findAll(PageRequest.of(startIndex, startIndex + numberOfElements, Sort.by("name"))).toList();
    }

    @Override
    public List<Dish> getDishesWithNameLike(String namePart, int numberOfElements, int startIndex) {
        return dishDAO.findByNameStartingWith(namePart,
                PageRequest.of(startIndex, startIndex + numberOfElements, Sort.by("name"))).toList();
    }

    @Override
    public boolean updateCaloriesAndMacrosByDishId(UUID dishId) throws NotFoundException {
        Dish dish = getDishById(dishId);
        dish.setDerivedCalories(countDishCalories(dish));
        dish.setDerivedCarbohydrates(countDishCarbohydrates(dish));
        dish.setDerivedFats(countDishFats(dish));
        dish.setDerivedProteins(countDishProteins(dish));
        dishDAO.save(dish);
        return true;
    }

    private double countDishCalories(Dish dish) {
        return dish.getDishProductDetails()
                .stream()
                .mapToDouble(pd -> pd.getAmount() * pd.getMeasureType().getNumberOfGrams() * pd.getProduct().getCaloriesPerGramInGram())
                .sum();
    }

    public double countDishCarbohydrates(Dish dish) {
        return dish.getDishProductDetails()
                .stream()
                .mapToDouble(pd -> pd.getAmount() * pd.getMeasureType().getNumberOfGrams() * pd.getProduct().getCarbohydratesPerGramInGram())
                .sum();
    }

    public double countDishProteins(Dish dish) {
        return dish.getDishProductDetails()
                .stream()
                .mapToDouble(pd -> pd.getAmount() * pd.getMeasureType().getNumberOfGrams() * pd.getProduct().getProteinsPerGramInGram())
                .sum();
    }

    public double countDishFats(Dish dish) {
        return dish.getDishProductDetails()
                .stream()
                .mapToDouble(pd -> pd.getAmount() * pd.getMeasureType().getNumberOfGrams() * pd.getProduct().getFatsPerGramInGrams())
                .sum();
    }

    @Override
    public Map<String, Map<String, Double>> countDishDetailsByDishId(UUID dishId) throws NotFoundException {
        Dish dish = getDishById(dishId);

        Map<String, Map<String, Double>> result = new HashMap<>();

        Map<String, Double> dishMacroDetails = new HashMap<>();
        Map<String, Double> dishVitaminDetails = new HashMap<>();
        Map<String, Double> dishMineralDetails = new HashMap<>();

        for(DishProductDetail d : dish.getDishProductDetails()){
            double actProductGrams = d.getAmount() * d.getMeasureType().getNumberOfGrams();

            List<MacroDetail> actualProductMacroDetails = d.getProduct().getMacroDetails();
            List<MineralDetail> actualProductMineralDetails = d.getProduct().getMineralDetails();
            List<VitaminDetail> actualProductVitaminDetails = d.getProduct().getVitaminDetails();

            actualProductMacroDetails
                    .forEach(md -> dishMacroDetails.put(md.getDetailedMacroType().toString(), (
                                            dishMacroDetails.get(md.getDetailedMacroType().toString()) == null
                                            ?
                                            actProductGrams * md.getMeasureType().getNumberOfGrams()
                                            :
                                            dishMacroDetails.get(md.getDetailedMacroType().toString())
                                                    + actProductGrams * md.getMeasureType().getNumberOfGrams())));
            actualProductMineralDetails
                    .forEach(m -> dishMineralDetails.put(m.getMineralType().toString(), (
                            dishMineralDetails.get(m.getMineralType().toString()) == null
                                    ?
                                    actProductGrams * m.getMeasureType().getNumberOfGrams()
                                    :
                                    dishMineralDetails.get(m.getMineralType().toString())
                                            + actProductGrams * m.getMeasureType().getNumberOfGrams())));
            actualProductVitaminDetails
                    .forEach(v -> dishVitaminDetails.put(v.getVitaminType().toString(), (
                            dishVitaminDetails.get(v.getVitaminType().toString()) == null
                                    ?
                                    actProductGrams * v.getMeasureType().getNumberOfGrams()
                                    :
                                    dishVitaminDetails.get(v.getVitaminType().toString())
                                            + actProductGrams * v.getMeasureType().getNumberOfGrams())));
        }
        result.put("macroDetails", dishMacroDetails);
        result.put("vitamins", dishVitaminDetails);
        result.put("minerals", dishMineralDetails);
        return result;
    }
}
