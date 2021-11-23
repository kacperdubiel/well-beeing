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

import java.util.*;
import java.util.stream.Collectors;

@Service("nutritionPlanService")
public class NutritionPlanServiceImpl implements NutritionPlanService {

    private final NutritionPlanDAO nutritionPlanDAO;
    private final NutritionPlanPositionDAO nutritionPlanPositionDAO;
    private final ProfileService profileService;
    private final NutritionPlanGenerationStrategy nutritionPlanGenerationStrategy;

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
    public boolean deleteCreatedNutritionPlanFromProfile(UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan != null){
            nutritionPlanDAO.deleteById(nutritionPlan.getId());
            return true;
        }
        else throw new NotFoundException("Nutrition plan with id: " + nutritionPlanId + " not found");
    }

    @Override
    public boolean deleteOwnedNutritionPlanFromProfile(UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan != null){
            nutritionPlan.setOwnerProfile(null);
            nutritionPlanDAO.save(nutritionPlan);
            return true;
        }
        else throw new NotFoundException("Nutrition plan with id: " + nutritionPlanId + " not found");
    }

    @Override
    public List<NutritionPlan> getAllCreatedByUserProfileNutritionPlans(UUID profileId) throws NotFoundException {
        Profile profile =  profileService.getProfileById(profileId);
        return nutritionPlanDAO.findAllByCreatorProfileIdAndOwnerProfileId(profile.getId(), profile.getId())
                .stream()
                .sorted(Comparator.comparing(NutritionPlan::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<NutritionPlan> getAllOwnedByUserProfileNutritionPlans(UUID profileId) throws NotFoundException {
        Profile profile =  profileService.getProfileById(profileId);
        List<NutritionPlan> result = nutritionPlanDAO.findAllByOwnerProfileId(profile.getId());
        return result.stream()
                .filter(np -> !np.getCreatorProfile().getId().equals(profile.getId()))
                .sorted(Comparator.comparing(NutritionPlan::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<NutritionPlan> getAllCreatedByDieticianProfileNutritionPlans(UUID profileId) throws NotFoundException {
        Profile profile =  profileService.getProfileById(profileId);
        List<NutritionPlan> result = nutritionPlanDAO.findAllByCreatorProfileId(profile.getId());
        return result.stream()
                .filter(np -> !np.getOwnerProfile().getId().equals(profile.getId()))
                .sorted(Comparator.comparing(NutritionPlan::getName))
                .collect(Collectors.toList());
    }

    @Override
    public NutritionPlan markNutritionPlanAsMain(UUID nutritionPlanId, UUID profileId) throws NotFoundException {
        NutritionPlan np = getNutritionPlanById(nutritionPlanId);
        if(np.isMain())
            return np;
        NutritionPlan actualMain = findUserMainNutritionPlan(profileId);
        if(actualMain != null){
            actualMain.setMain(false);
            nutritionPlanDAO.save(actualMain);
        }
        np.setMain(true);
        return nutritionPlanDAO.save(np);
    }

    private NutritionPlan findUserMainNutritionPlan(UUID profileId) throws NotFoundException {
        List<NutritionPlan> owned = getAllOwnedByUserProfileNutritionPlans(profileId);
        Optional<NutritionPlan> main = owned.stream().filter(NutritionPlan::isMain).findFirst();
        if(!main.isPresent()){
            List<NutritionPlan> created = getAllCreatedByUserProfileNutritionPlans(profileId);
            main = created.stream().filter(NutritionPlan::isMain).findFirst();
        }
        return main.orElse(null);
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
    public NutritionPlan addEmptyNutritionPlanToProfile(UUID profileId, String name) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        NutritionPlan nutritionPlan = NutritionPlan.builder()
                .generationDate(new Date())
                .ownerProfile(profile)
                .creatorProfile(profile)
                .name(name)
                .nutritionPlanPositions(new ArrayList<>())
                .build();
        NutritionPlan mainNutritionPlan = findUserMainNutritionPlan(profileId);
        if(mainNutritionPlan == null)
            nutritionPlan.setMain(true);
        nutritionPlanDAO.save(nutritionPlan);
        return nutritionPlan;
    }

    @Override
    public NutritionPlan addNutritionPlanToProfile(NutritionPlan nutritionPlan, UUID profileId) throws NotFoundException {
        Profile profile = profileService.getProfileById(profileId);
        nutritionPlan.setId(null);
        nutritionPlan.setCreatorProfile(profile);
        NutritionPlan mainNutritionPlan = findUserMainNutritionPlan(profileId);
        if(mainNutritionPlan == null)
            nutritionPlan.setMain(true);
        nutritionPlanDAO.save(nutritionPlan);
        return nutritionPlan;
    }

    @Override
    public NutritionPlan addPositionToProfileNutritionPlan(NutritionPlanPosition position, UUID nutritionPlanId) throws NotFoundException {
        NutritionPlan nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        if(nutritionPlan != null){
            position.setNutritionPlan(nutritionPlan);
            nutritionPlanPositionDAO.save(position);
            nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
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
        nutritionPlanPositionDAO.save(nutritionPlanPosition);
        nutritionPlanPositionDAO.deleteById(positionId);
        nutritionPlan = nutritionPlanDAO.findById(nutritionPlanId).orElse(null);
        return nutritionPlan;
    }
}
