package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureTypeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MeasureTypeController {
    private MeasureTypeService measureTypeService;

    public MeasureTypeController(@Qualifier("measureTypeService") MeasureTypeService measureTypeService){
        this.measureTypeService = measureTypeService;
    }

    @RequestMapping(path = "measure-types", method = RequestMethod.GET)
    public ResponseEntity<?> getMeasureTypes(){
        List<MeasureType> measureTypesResult = measureTypeService.getMeasureTypes();
        return new ResponseEntity<>(measureTypesResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measure-types/{id}", method = RequestMethod.GET)
    public ResponseEntity<MeasureType> getMeasureTypeById(@PathVariable("id") UUID id)
            throws NotFoundException
    {
        MeasureType measureTypeResult = measureTypeService.getMeasureTypeById(id);
        return new ResponseEntity<>(measureTypeResult, HttpStatus.OK);
    }

    @RolesAllowed(ERole.Name.ROLE_DOCTOR)
    @RequestMapping(path = "measure-types", method = RequestMethod.POST)
    public ResponseEntity<MeasureType> addMeasureType(@RequestBody @NonNull MeasureType measureType)
            throws ConflictException
    {
        MeasureType measureTypeResult = measureTypeService.addMeasureType(measureType);
        return new ResponseEntity<>(measureTypeResult, HttpStatus.CREATED);
    }

    @RolesAllowed(ERole.Name.ROLE_DOCTOR)
    @RequestMapping(path = "measure-types", method = RequestMethod.PUT)
    public ResponseEntity<MeasureType> updateMeasureType(@RequestBody @NonNull MeasureType measureType)
            throws NotFoundException
    {
        MeasureType measureTypeResult = measureTypeService.updateMeasureType(measureType);
        return new ResponseEntity<>(measureTypeResult, HttpStatus.OK);
    }

    @RolesAllowed(ERole.Name.ROLE_DOCTOR)
    @RequestMapping(path = "measure-types/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMeasureType(@PathVariable("id") UUID id) throws NotFoundException {
        measureTypeService.deleteMeasureTypeById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
