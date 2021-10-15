package com.wellbeeing.wellbeeing.service.diet;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.EBMIResult;
import com.wellbeeing.wellbeeing.domain.diet.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.repository.account.ProfileCardDAO;
import com.wellbeeing.wellbeeing.repository.diet.AilmentDAO;
import com.wellbeeing.wellbeeing.repository.diet.DietDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProfileDietCalculationDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("profileDietCalculationService")
public class ProfileDietCalculationService implements ProfileDietCalculationServiceApi {

    private ProfileCardDAO profileCardDAO;
    private DietDAO dietDAO;
    private AilmentDAO ailmentDAO;
    private ProfileDietCalculationDAO profileDietCalculationDAO;

    private BasicMetabolismStrategy basicMetabolismStrategy;
    private CompleteMetabolismStrategy completeMetabolismStrategy;

    public ProfileDietCalculationService(@Qualifier("profileCardDAO") ProfileCardDAO profileCardDAO,
                                         @Qualifier("dietDAO") DietDAO dietDAO,
                                         @Qualifier("ailmentDAO") AilmentDAO ailmentDAO,
                                         @Qualifier("profileDietCalculationDAO") ProfileDietCalculationDAO profileDietCalculationDAO){
        this.profileCardDAO = profileCardDAO;
        this.dietDAO = dietDAO;
        this.ailmentDAO = ailmentDAO;
        this.profileDietCalculationDAO = profileDietCalculationDAO;
        this.basicMetabolismStrategy = new HarrisBenedictBasicMetabolismStrategy();
        this.completeMetabolismStrategy = new PalCompleteMetabolismStrategy();
    }

    private void setBasicMetabolismStrategy(BasicMetabolismStrategy basicMetabolismStrategy){
        this.basicMetabolismStrategy = basicMetabolismStrategy;
    }

    private void setCompleteMetabolismStrategy(CompleteMetabolismStrategy completeMetabolismStrategy){
        this.completeMetabolismStrategy = completeMetabolismStrategy;
    }

    private double countBmi(double weight, double height){
        return weight / Math.pow(height, 2);
    }

    private EBMIResult decideBmiResultType(double bmiValue, int age){
            if(bmiValue < 18.5)
                return EBMIResult.UNDERWEIGHT;
            if(bmiValue < 24.99)
                return EBMIResult.HEALTHY;
            if(bmiValue < 29.99)
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
    public ProfileDietCalculation calculateAllSuggestionsForCard(UUID profileCardId) {
        ProfileCard profileCard = profileCardDAO.findById(profileCardId).orElse(null);
        if(profileCard != null) {
            double bmi = countBmi(profileCard.getWeight(), profileCard.getHeight());
            EBMIResult bmiResultType = decideBmiResultType(bmi, profileCard.getAge());
            double basicMetabolism = basicMetabolismStrategy.calculateBasicMetabolism(profileCard.getAge(),
                    profileCard.getESex(), profileCard.getWeight(), profileCard.getHeight());
            double completeMetabolism = completeMetabolismStrategy.calculateCompleteMetabolism(basicMetabolism,
                    profileCard.getActivityLevel(), profileCard.getTrainingActivityTimePerWeek());
            int changeDueToAilments = countChangeInCaloriesDueToAilments(profileCard.getAilments());
            int changeDueToGoal = profileCard.getDietGoal().getChangeInCalories();
        }
        return null;
    }

    @Override
    public ProfileDietCalculation getProfileDietCalculationByCard(UUID profileCardId) {
        return null;
    }

    @Override
    public ProfileDietCalculation getProfileDietCalculation(UUID profileDietCalculationId) {
        return null;
    }
}
