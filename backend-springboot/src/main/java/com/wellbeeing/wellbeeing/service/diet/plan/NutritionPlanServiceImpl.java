package com.wellbeeing.wellbeeing.service.diet.plan;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.NutritionPlanPosition;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.exception.NutritionPlanGenerationException;
import com.wellbeeing.wellbeeing.repository.diet.NutritionPlanDAO;
import com.wellbeeing.wellbeeing.repository.diet.NutritionPlanPositionDAO;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("nutritionPlanService")
public class NutritionPlanServiceImpl implements NutritionPlanService {

    private NutritionPlanDAO nutritionPlanDAO;
    private NutritionPlanPositionDAO nutritionPlanPositionDAO;
    private ProfileService profileService;
    private NutritionPlanGenerationStrategy nutritionPlanGenerationStrategy;

    @Autowired
    public NutritionPlanServiceImpl(@Qualifier("nutritionPlanDAO") NutritionPlanDAO nutritionPlanDAO,
                                    @Qualifier("profileService") ProfileService profileService,
                                    @Qualifier("nutritionPlanPositionDAO")
                                            NutritionPlanPositionDAO nutritionPlanPositionDAO){
        this.nutritionPlanDAO = nutritionPlanDAO;
        this.nutritionPlanPositionDAO = nutritionPlanPositionDAO;
        this.profileService = profileService;
        this.nutritionPlanGenerationStrategy = new SimpleNutritionPlanGenerationStrategy();
    }

    @Override
    public boolean deleteNutritionPlanFromProfile(UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan != null){
            nutritionPlan.setProfile(null);
            nutritionPlanDAO.save(nutritionPlan);
            nutritionPlanDAO.delete(nutritionPlan);
            return true;
        }
        else throw new NotFoundException("Nutrition plan with id: " + nutritionPlanId + " not found");
    }

    @Override
    public List<NutritionPlan> getAllProfileNutritionPlans(UUID profileId) throws NotFoundException {
        Profile profile =  profileService.getProfileById(profileId);
        return profile.getNutritionPlans();
    }

    @Override
    public NutritionPlan getNutritionPlanById(UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan != null)
            return nutritionPlan;
        throw new NotFoundException("Nutrition plan with id: " + nutritionPlanId + " not found");
    }

    @Override
    public NutritionPlan generateNutritionPlanForProfile(UUID profileId) throws NotFoundException, NutritionPlanGenerationException {
        Profile profile = profileService.getProfileById(profileId);
        return nutritionPlanGenerationStrategy.generateNutritionPlan(profile);
    }

    @Override
    public NutritionPlan addEmptyNutritionPlanToProfile(UUID profileId) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        NutritionPlan nutritionPlan = NutritionPlan.builder()
                .generationDate(new Date())
                .profile(profile)
                .build();
        nutritionPlanDAO.save(nutritionPlan);
        return nutritionPlan;
    }

    @Override
    public NutritionPlan addNutritionPlanToProfile(NutritionPlan nutritionPlan, UUID profileId) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        nutritionPlan.setId(null);
        nutritionPlan.setProfile(profile);
        nutritionPlanDAO.save(nutritionPlan);
        return nutritionPlan;
    }

    @Override
    public NutritionPlan addPositionToProfileNutritionPlan(NutritionPlanPosition position, UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan != null){
            position.setNutritionPlan(nutritionPlan);
            nutritionPlanPositionDAO.save(position);
            return nutritionPlan;
        }
        else throw new NotFoundException("Nutrition plan with id: " +  nutritionPlanId + " not found");
    }

    @Override
    public NutritionPlan updatePositionInProfileNutritionPlan(NutritionPlanPosition position, UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan == null)
            throw new NotFoundException("Nutrition plan with id: " +  nutritionPlanId + " not found");
        NutritionPlanPosition nutritionPlanPosition = nutritionPlan.getNutritionPlanPositions().stream()
                .filter(p -> p.getId().equals(position.getId())).findFirst().orElse(null);
        if(nutritionPlanPosition == null)
            throw new NotFoundException("Nutrition plan position with id: " +  position.getId() + " not found for nutrition plan with id: " + nutritionPlanId);
        nutritionPlanPosition.setNutritionPlan(nutritionPlan);
        nutritionPlanPositionDAO.save(nutritionPlanPosition);
        return nutritionPlan;
    }

    @Override
    public NutritionPlan deletePositionFromProfileNutritionPlan(UUID positionId, UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan == null)
            throw new NotFoundException("Nutrition plan with id: " +  nutritionPlanId + " not found");
        NutritionPlanPosition nutritionPlanPosition = nutritionPlan.getNutritionPlanPositions().stream()
                .filter(p -> p.getId().equals(positionId)).findFirst().orElse(null);
        if(nutritionPlanPosition == null)
            throw new NotFoundException("Nutrition plan position with id: " +  positionId + " not found for nutrition plan with id: " + nutritionPlanId);
        nutritionPlanPosition.setNutritionPlan(null);
        nutritionPlanPositionDAO.save(nutritionPlanPosition);
        nutritionPlanPositionDAO.delete(nutritionPlanPosition);
        return nutritionPlan;
    }
}
