package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.diet.NutritionLabelService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NutritionLabelController {
    private final NutritionLabelService nutritionLabelService;
    private final UserService userService;

    public NutritionLabelController(@Qualifier("nutritionLabelService") NutritionLabelService nutritionLabelService,
                                    @Qualifier("userService") UserService userService){
        this.nutritionLabelService = nutritionLabelService;
        this.userService = userService;
    }

    @RequestMapping(path = "/nutrition-label/{labelId}", method = RequestMethod.GET)
    public ResponseEntity<?> getLabelById(@PathVariable("labelId") UUID labelID) throws NotFoundException {
        NutritionLabel nutritionLabel = nutritionLabelService.getNutritionLabelById(labelID);
        return new ResponseEntity<>(nutritionLabel, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-label", method = RequestMethod.GET)
    public ResponseEntity<?> getAllNutritionLabels() {
        return new ResponseEntity<>(nutritionLabelService.getNutritionLabels(), HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-label/my", method = RequestMethod.GET)
    public ResponseEntity<?> getDieticianNutritionLabels(Principal principal,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        UUID dieticianId = userService.findUserIdByUsername(principal.getName());
        Page<NutritionLabel> labelsPage = nutritionLabelService.getActiveNutritionLabelsByCreatorId(dieticianId, page, size);
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(labelsPage.getNumber())
                .totalItems(labelsPage.getTotalElements())
                .totalPages(labelsPage.getTotalPages())
                .objects(labelsPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-label", method = RequestMethod.POST)
    public ResponseEntity<?> addNutritionLabel(@RequestBody @NonNull NutritionLabel label) throws ConflictException {
        return new ResponseEntity<>(nutritionLabelService.addNutritionLabel(label), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/nutrition-label/{labelId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateNutritionLabel(Principal principal, @RequestBody @NonNull NutritionLabel label, @PathVariable("labelId") UUID labelId) throws NotFoundException, ForbiddenException, ConflictException {
        NutritionLabel actualLabel = nutritionLabelService.getNutritionLabelById(labelId);
        if(!actualLabel.getCreator().getId().equals(userService.findUserIdByUsername(principal.getName()))){
            throw new ForbiddenException("You dont have permissions to update label with id" + labelId);
        }
        return new ResponseEntity<>(nutritionLabelService.updateNutritionLabel(label, labelId), HttpStatus.OK);
    }

    @RequestMapping(path = "/nutrition-label/{labelId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNutritionLabel(Principal principal, @PathVariable("labelId") UUID labelId) throws NotFoundException, ForbiddenException {
        NutritionLabel actualLabel = nutritionLabelService.getNutritionLabelById(labelId);
        if(!actualLabel.getCreator().getId().equals(userService.findUserIdByUsername(principal.getName()))){
            throw new ForbiddenException("You dont have permissions to update label with id" + labelId);
        }
        return new ResponseEntity<>(nutritionLabelService.deleteNutritionLabel(labelId), HttpStatus.OK);
    }
}
