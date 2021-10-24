package com.wellbeeing.wellbeeing.api.telemedic;


import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.telemedic.EConnectionType;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureTypeService;
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
    public ResponseEntity<?> getMeasureById(@PathVariable("id") UUID id, Principal principal)
            throws NotFoundException, ForbiddenException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Measure measureResult = measureService.getMeasureById(id);

        if(!measureResult.getOwner().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        return new ResponseEntity<>(measureResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measures/type/{type_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserMeasures(@PathVariable("type_id") UUID measureTypeId, Principal principal)
            throws NotFoundException
    {
        MeasureType measuresType = measureTypeService.getMeasureTypeById(measureTypeId);
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile measuresOwner = profileService.getProfileById(userId);

        List<Measure> measuresResult = measureService.getMeasuresByProfileAndMeasureType(measuresOwner, measuresType);

        // Sorted by measure date DESC
        measuresResult.sort((Measure m1, Measure m2) -> (-1 * (m1.getMeasureDate().compareTo(m2.getMeasureDate()))));
        return new ResponseEntity<>(measuresResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measures", method = RequestMethod.POST)
    public ResponseEntity<?> addMeasure(@RequestBody @NonNull Measure measure, Principal principal)
            throws ForbiddenException, ConflictException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        if(!measure.getOwner().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        Measure measureResult = measureService.addMeasure(measure);
        return new ResponseEntity<>(measureResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "measures", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMeasure(@RequestBody @NonNull Measure measure, Principal principal)
            throws ForbiddenException, NotFoundException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        if(!measure.getOwner().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        Measure measureResult = measureService.updateMeasure(measure);
        return new ResponseEntity<>(measureResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measures/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMeasure(@PathVariable("id") UUID id, Principal principal)
            throws NotFoundException, ForbiddenException
    {
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Measure measure = measureService.getMeasureById(id);

        if(!measure.getOwner().getId().equals(userId)){
            throw new ForbiddenException("You do not have access rights to do that!");
        }

        measureService.deleteMeasureById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
