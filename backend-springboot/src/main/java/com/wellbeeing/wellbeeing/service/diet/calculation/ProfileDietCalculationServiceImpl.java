package com.wellbeeing.wellbeeing.service.diet.calculation;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.calculation.DietCalcMealSuggestions;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.diet.type.EBMIResult;
import com.wellbeeing.wellbeeing.domain.diet.type.EBasicMacro;
import com.wellbeeing.wellbeeing.domain.diet.type.EGlycemicIndexLevel;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.account.ProfileDAO;
import com.wellbeeing.wellbeeing.repository.diet.AilmentDAO;
import com.wellbeeing.wellbeeing.repository.diet.DietDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProfileDietCalculationDAO;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("profileDietCalculationService")
public class ProfileDietCalculationServiceImpl implements ProfileDietCalculationService {

    private ProfileCardDAO profileCardDAO;
    private ProfileDAO profileDAO;
    private ProfileDietCalculationDAO profileDietCalculationDAO;

    private BasicMetabolismStrategy basicMetabolismStrategy;
    private CompleteMetabolismStrategy completeMetabolismStrategy;
    private MacrosCalcStrategy macrosCalcStrategy;
    private GlycemicIndexCalcStrategy glycemicIndexCalcStrategy;


    @Autowired
    public ProfileDietCalculationServiceImpl(@Qualifier("profileCardDAO") ProfileCardDAO profileCardDAO,
                                             @Qualifier("dietDAO") DietDAO dietDAO,
                                             @Qualifier("ailmentDAO") AilmentDAO ailmentDAO,
                                             @Qualifier("profileDietCalculationDAO") ProfileDietCalculationDAO profileDietCalculationDAO,
                                             @Qualifier("profileDAO") ProfileDAO profileDAO){
        this.profileCardDAO = profileCardDAO;
        this.profileDietCalculationDAO = profileDietCalculationDAO;
        this.profileDAO = profileDAO;

        this.basicMetabolismStrategy = new HarrisBenedictBasicMetabolismStrategy();
        this.completeMetabolismStrategy = new PalCompleteMetabolismStrategy();
        this.macrosCalcStrategy = new BasicMacrosCalcStrategy();
        this.glycemicIndexCalcStrategy = new BasicGlycemicIndexCalcStrategy();
    }

    private void setBasicMetabolismStrategy(BasicMetabolismStrategy basicMetabolismStrategy){
        this.basicMetabolismStrategy = basicMetabolismStrategy;
    }

    private void setCompleteMetabolismStrategy(CompleteMetabolismStrategy completeMetabolismStrategy){
        this.completeMetabolismStrategy = completeMetabolismStrategy;
    }

    private void setMacrosCalcStrategy(MacrosCalcStrategy macrosCalcStrategy){
        this.macrosCalcStrategy = macrosCalcStrategy;
    }

    public void setGlycemicIndexCalcStrategy(GlycemicIndexCalcStrategy glycemicIndexCalcStrategy) {
        this.glycemicIndexCalcStrategy = glycemicIndexCalcStrategy;
    }

    private double countBmi(double weight, double height){
        return weight / Math.pow(height, 2);
    }

    private EBMIResult decideBmiResultType(double bmiValue){
            if(bmiValue < EBMIResult.SEVERELY_UNDERWEIGHT.getHigherRange())
                return EBMIResult.UNDERWEIGHT;
            if(bmiValue < EBMIResult.UNDERWEIGHT.getHigherRange())
                return EBMIResult.UNDERWEIGHT;
            if(bmiValue < EBMIResult.HEALTHY.getHigherRange())
                return EBMIResult.HEALTHY;
            if(bmiValue < EBMIResult.OVERWEIGHT.getHigherRange())
                return EBMIResult.OVERWEIGHT;
            else
                return EBMIResult.OBESE;
    }

    private int countChangeInCaloriesDueToAilments(List<Ailment> ailments){
        int result = 0;
        for (Ailment a: ailments) {
            result += a.getChangeInCalories();
        }
        return result;
    }

    @Override
    public ProfileDietCalculation calculateAllSuggestionsByProfileCardId(UUID profileCardId) {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if(profileCard != null) {
            double bmi = countBmi(profileCard.getWeight(), profileCard.getHeight());
            EBMIResult bmiResultType = decideBmiResultType(bmi);
            double basicMetabolism = basicMetabolismStrategy.calculateBasicMetabolism(profileCard);
            double completeMetabolism = completeMetabolismStrategy.calculateCompleteMetabolism(basicMetabolism, profileCard);

            int changeDueToAilments = countChangeInCaloriesDueToAilments(profileCard.getAilments());
            int changeDueToGoal = profileCard.getDietGoal().getChangeInCalories();
            int changeDueToBmiResult = bmiResultType.getSuggestedChangeInCalories();

            double suggestedCalories = completeMetabolism + changeDueToAilments + changeDueToGoal + changeDueToBmiResult;

            HashMap<EBasicMacro, Double> macros = macrosCalcStrategy.calculateMacros(completeMetabolism, profileCard);
            double suggestedCarbohydrates = macros.get(EBasicMacro.CARBOHYDRATES);
            double suggestedFats = macros.get(EBasicMacro.FATS);
            double suggestedProteins = macros.get(EBasicMacro.PROTEINS);

            double suggestedBreakfastCalories = EMealType.BREAKFAST.getSuggestedDailyCaloriesPars() * suggestedCalories;
            double suggestedLunchCalories = EMealType.LUNCH.getSuggestedDailyCaloriesPars() * suggestedCalories;
            double suggestedDinnerCalories = EMealType.DINNER.getSuggestedDailyCaloriesPars() * suggestedCalories;
            double suggestedSnackCalories = EMealType.SNACK.getSuggestedDailyCaloriesPars() * suggestedCalories;
            double suggestedSupperCalories = EMealType.SUPPER.getSuggestedDailyCaloriesPars() * suggestedCalories;

            HashMap<EMealType, EGlycemicIndexLevel> suggestedGlycemicIndexForMeals =
                    glycemicIndexCalcStrategy.countGlycemicIndexesForMeals(profileCard);
            EGlycemicIndexLevel suggestedGlycemicForBreakfast = suggestedGlycemicIndexForMeals.get(EMealType.BREAKFAST);
            EGlycemicIndexLevel suggestedGlycemicForLunch = suggestedGlycemicIndexForMeals.get(EMealType.LUNCH);
            EGlycemicIndexLevel suggestedGlycemicForDinner = suggestedGlycemicIndexForMeals.get(EMealType.DINNER);
            EGlycemicIndexLevel suggestedGlycemicForSnack = suggestedGlycemicIndexForMeals.get(EMealType.SNACK);
            EGlycemicIndexLevel suggestedGlycemicForSupper = suggestedGlycemicIndexForMeals.get(EMealType.SUPPER);

            ProfileDietCalculation profileDietCalculation = ProfileDietCalculation.builder()
                    .bmi(bmi)
                    .bmiResultType(bmiResultType)
                    .basicMetabolism(basicMetabolism)
                    .suggestedCalories(suggestedCalories)
                    .suggestedCarbohydrates(suggestedCarbohydrates)
                    .suggestedFats(suggestedFats)
                    .suggestedProteins(suggestedProteins)
                    .dietCalcMealSuggestions(new ArrayList<>())
                    .build();

            profileDietCalculation.getDietCalcMealSuggestions().add(DietCalcMealSuggestions.builder()
                    .mealType(EMealType.BREAKFAST)
                    .numberOfCalories(suggestedBreakfastCalories)
                    .glycemicIndexLevel(suggestedGlycemicForBreakfast)
                    .dietCalculation(profileDietCalculation)
                    .build());
            profileDietCalculation.getDietCalcMealSuggestions().add(DietCalcMealSuggestions.builder()
                    .mealType(EMealType.LUNCH)
                    .numberOfCalories(suggestedLunchCalories)
                    .glycemicIndexLevel(suggestedGlycemicForLunch)
                    .dietCalculation(profileDietCalculation)
                    .build());
            profileDietCalculation.getDietCalcMealSuggestions().add(DietCalcMealSuggestions.builder()
                    .mealType(EMealType.DINNER)
                    .numberOfCalories(suggestedDinnerCalories)
                    .glycemicIndexLevel(suggestedGlycemicForDinner)
                    .dietCalculation(profileDietCalculation)
                    .build());
            profileDietCalculation.getDietCalcMealSuggestions().add(DietCalcMealSuggestions.builder()
                    .mealType(EMealType.SNACK)
                    .numberOfCalories(suggestedSnackCalories)
                    .glycemicIndexLevel(suggestedGlycemicForSnack)
                    .dietCalculation(profileDietCalculation)
                    .build());
            profileDietCalculation.getDietCalcMealSuggestions().add(DietCalcMealSuggestions.builder()
                    .mealType(EMealType.SUPPER)
                    .numberOfCalories(suggestedSupperCalories)
                    .glycemicIndexLevel(suggestedGlycemicForSupper)
                    .dietCalculation(profileDietCalculation)
                    .build());
            return profileDietCalculation;
        }
        return null;
    }

    @Override
    public ProfileDietCalculation getDietCalculationById(UUID profileDietCalculationId) throws NotFoundException {
        ProfileDietCalculation actCalc = profileDietCalculationDAO.findById(profileDietCalculationId).orElse(null);
        if (actCalc != null)
            return actCalc;
        throw new NotFoundException("Profile diet calculation with id: " + profileDietCalculationId + " not found");
    }

    @Override
    public ProfileDietCalculation getDietCalculationByProfileCardId(UUID profileCardId) throws NotFoundException {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if(profileCard != null){
            return profileCard.getDietCalculations();
        }
        throw new NotFoundException("Profile card with id: " + profileCardId + " not found");
    }

    @Override
    public ProfileDietCalculation updateDietCalculationByProfileCardId(UUID profileCardId) throws NotFoundException {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if(profileCard != null){
            ProfileDietCalculation calcForProfileCard = calculateAllSuggestionsByProfileCardId(profileCardId);
            /*ProfileDietCalculation actCalc = profileCard.getDietCalculations();
            if(actCalc != null){
                profileDietCalculationDAO.deleteById(actCalc.getId());
                calcForProfileCard.setId(actCalc.getId());
            }*/
            profileDietCalculationDAO.save(calcForProfileCard);
            profileCard.setDietCalculations(calcForProfileCard);
            profileCardDAO.save(profileCard);
            return calcForProfileCard;
        }
       throw new NotFoundException("Profile card with id: " + profileCardId + " not found");
    }

    @Override
    public ProfileDietCalculation getDietCalculationByProfileId(UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if(actProfile != null)
            return getDietCalculationByProfileCardId(actProfile.getProfileCard().getId());
        throw new NotFoundException("Profile with id: " + profileId + " not found");
    }

    @Override
    public ProfileDietCalculation updateDietCalculationByProfileId(UUID profileId) throws NotFoundException {
        Profile actProfile = profileDAO.findById(profileId).orElse(null);
        if(actProfile != null)
            updateDietCalculationByProfileCardId(actProfile.getProfileCard().getId());
        throw new NotFoundException("Profile with id: " + profileId + " not found");
    }
}
