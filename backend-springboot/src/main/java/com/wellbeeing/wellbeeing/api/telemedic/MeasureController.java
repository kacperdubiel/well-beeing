package com.wellbeeing.wellbeeing.api.telemedic;


import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureTypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MeasureController {
    private MeasureService measureService;
    private MeasureTypeService measureTypeService;
    private UserService userService;
    private ProfileService profileService;

    public MeasureController(@Qualifier("measureService") MeasureService measureService,
                             @Qualifier("measureTypeService") MeasureTypeService measureTypeService,
                             @Qualifier("userService") UserService userService,
                             @Qualifier("profileService") ProfileService profileService
    ){
        this.measureService = measureService;
        this.measureTypeService = measureTypeService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "measures/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMeasureById(@PathVariable("id") UUID id, Principal principal){
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Measure measureResult = measureService.getMeasureById(id);

        if(measureResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        if(measureResult.getOwner().getId() != userId){
            return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                    "403"), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(measureResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measures/type/{type_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserMeasures(@PathVariable("type_id") UUID measureTypeId, Principal principal){
        try {
            MeasureType measuresType = measureTypeService.getMeasureTypeById(measureTypeId);
            UUID userId = userService.findUserIdByUsername(principal.getName());
            Profile measuresOwner = profileService.getProfileById(userId);

            List<Measure> measuresResult = measureService.getMeasuresByProfileAndMeasureType(measuresOwner, measuresType);
            if (measuresResult == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            else {
                // Sorted by measure date DESC
                measuresResult.sort((Measure m1, Measure m2) -> (-1 * (m1.getMeasureDate().compareTo(m2.getMeasureDate()))));
                return new ResponseEntity<>(measuresResult, HttpStatus.OK);
            }
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                    "404"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "measures", method = RequestMethod.POST)
    public ResponseEntity<?> addMeasure(@RequestBody @NonNull Measure measure, Principal principal){
        try {
            UUID userId = userService.findUserIdByUsername(principal.getName());
            if(measure.getOwner().getId() != userId){
                return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                        "403"), HttpStatus.FORBIDDEN);
            }

            Measure measureResult = measureService.addMeasure(measure);
            return new ResponseEntity<>(measureResult, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "measures", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMeasure(@RequestBody @NonNull Measure measure, Principal principal){
        try {
            UUID userId = userService.findUserIdByUsername(principal.getName());
            if(measure.getOwner().getId() != userId){
                return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                        "403"), HttpStatus.FORBIDDEN);
            }

            Measure measureResult = measureService.updateMeasure(measure);
            if(measureResult != null)
                return new ResponseEntity<>(measureResult, HttpStatus.OK);
            else
                return new ResponseEntity<>("Measure not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "measures/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMeasure(@PathVariable("id") UUID id, Principal principal){
        try {
            UUID userId = userService.findUserIdByUsername(principal.getName());
            Measure measure = measureService.getMeasureById(id);

            if(measure == null){
                return new ResponseEntity<>("Measure not found.", HttpStatus.NOT_FOUND);
            }

            if(measure.getOwner().getId() != userId){
                return new ResponseEntity<>(new ErrorMessage("You do not have access rights to do that!",
                        "403"), HttpStatus.FORBIDDEN);
            }

            boolean deleteResult = measureService.deleteMeasureById(id);
            return new ResponseEntity<>(deleteResult, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                    "500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
