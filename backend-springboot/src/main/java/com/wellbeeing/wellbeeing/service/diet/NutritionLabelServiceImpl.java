package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.diet.NutritionLabelDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("nutritionLabelService")
public class NutritionLabelServiceImpl implements NutritionLabelService {
    private final NutritionLabelDAO nutritionLabelDAO;

    public NutritionLabelServiceImpl(@Qualifier("nutritionLabelDAO") NutritionLabelDAO nutritionLabelDAO){
        this.nutritionLabelDAO = nutritionLabelDAO;
    }
    @Override
    public List<NutritionLabel> getNutritionLabels() {
        return nutritionLabelDAO.findAllByActive(true);
    }

    @Override
    public NutritionLabel getNutritionLabelById(UUID labelId) throws NotFoundException {
        NutritionLabel nutritionLabel = nutritionLabelDAO.findById(labelId).orElse(null);
        if(nutritionLabel != null)
            return nutritionLabel;
        throw new NotFoundException("Nutrition label with id: " + labelId + " not found");
    }

    @Override
    public NutritionLabel addNutritionLabel(NutritionLabel label) throws ConflictException {
        checkName(label.getName());
        return nutritionLabelDAO.save(label);
    }

    @Override
    public NutritionLabel updateNutritionLabel(NutritionLabel nutritionLabel, UUID nutritionLabelId) throws NotFoundException, ConflictException {
        NutritionLabel nutritionLabelToUpdate = getNutritionLabelById(nutritionLabelId);
        if(!nutritionLabelToUpdate.getName().equals(nutritionLabel.getName())){
            checkName(nutritionLabel.getName());
        }
        nutritionLabelToUpdate.setName(nutritionLabel.getName());
        nutritionLabelToUpdate.setDescription(nutritionLabel.getDescription());
        nutritionLabelToUpdate.setLabeledDiets(nutritionLabel.getLabeledDiets());
        nutritionLabelToUpdate.setLabeledAilments(nutritionLabel.getLabeledAilments());
        return nutritionLabelDAO.save(nutritionLabelToUpdate);
    }

    private void checkName(String name) throws ConflictException {
        if (nutritionLabelDAO.findAllByNameAndActive(name, true).size() > 0){
            throw new ConflictException("Label with name" + name + " already exists.");
        }
    }

    @Override
    public Page<NutritionLabel> getActiveNutritionLabelsByCreatorId(UUID creatorId, int page, int size){
        return nutritionLabelDAO.findAllByCreatorIdAndActive(creatorId, true, PageRequest.of(page, size, Sort.by("name")));
    }

    @Override
    public NutritionLabel deleteNutritionLabel(UUID nutritionLabelId) throws NotFoundException {
        NutritionLabel nutritionLabelToDelete = getNutritionLabelById(nutritionLabelId);
        nutritionLabelToDelete.setActive(false);
        return nutritionLabelDAO.save(nutritionLabelToDelete);
    }
}
