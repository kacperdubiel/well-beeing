package com.wellbeeing.wellbeeing.api.telemedic;


import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.domain.telemedic.*;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.account.UserService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureTypeService;
import com.wellbeeing.wellbeeing.service.telemedic.ProfileConnectionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    private ProfileConnectionService profileConnService;

    public MeasureController(@Qualifier("measureService") MeasureService measureService,
                             @Qualifier("measureTypeService") MeasureTypeService measureTypeService,
                             @Qualifier("userService") UserService userService,
                             @Qualifier("profileService") ProfileService profileService,
                             @Qualifier("profileConnectionService") ProfileConnectionService profileConnService
    ){
        this.measureService = measureService;
        this.measureTypeService = measureTypeService;
        this.userService = userService;
        this.profileService = profileService;
        this.profileConnService = profileConnService;
    }

    @RequestMapping(path = "measures/user/{user_id}/type/{type_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserMeasuresByType(@PathVariable("type_id") UUID measureTypeId,
                                                   @PathVariable("user_id") UUID measuresOwnerId, Principal principal,
                                                   @RequestParam(value = "page", defaultValue = "0") String page,
                                                   @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException, ForbiddenException
    {
        UUID authorizedUserId = userService.findUserIdByUsername(principal.getName());
        Profile authorizedUser = profileService.getProfileById(authorizedUserId);
        MeasureType measuresType = measureTypeService.getMeasureTypeById(measureTypeId);
        Profile measuresOwner = profileService.getProfileById(measuresOwnerId);

        if(!measuresOwnerId.equals(authorizedUserId)){
            ProfileConnection pConnResult = profileConnService.getProfileConnectionByProfileAndConnectedWithAndType(
                    measuresOwner, authorizedUser, EConnectionType.WITH_DOCTOR);

            if(pConnResult == null || !pConnResult.isAccepted()){
                throw new ForbiddenException("You do not have access rights to do that!");
            }
        }
        PaginatedResponse response = getPaginatedResponse(Integer.parseInt(page), Integer.parseInt(size),
                measuresType, measuresOwner);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PaginatedResponse getPaginatedResponse(int page, int size, MeasureType measuresType, Profile measuresOwner) {
        Page<Measure> measuresPage = measureService.getMeasuresByProfileAndMeasureType(measuresOwner, measuresType,
                page, size);
        return PaginatedResponse.builder()
                .currentPage(measuresPage.getNumber())
                .totalItems(measuresPage.getTotalElements())
                .totalPages(measuresPage.getTotalPages())
                .objects(measuresPage.getContent())
                .build();
    }

    @RequestMapping(path = "measures/type/{type_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMeasuresByType(@PathVariable("type_id") UUID measureTypeId, Principal principal,
                                               @RequestParam(value = "page", defaultValue = "0") String page,
                                               @RequestParam(value = "size", defaultValue = "10") String size)
            throws NotFoundException
    {
        MeasureType measuresType = measureTypeService.getMeasureTypeById(measureTypeId);
        UUID userId = userService.findUserIdByUsername(principal.getName());
        Profile measuresOwner = profileService.getProfileById(userId);

        PaginatedResponse response = getPaginatedResponse(Integer.parseInt(page), Integer.parseInt(size),
                measuresType, measuresOwner);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
