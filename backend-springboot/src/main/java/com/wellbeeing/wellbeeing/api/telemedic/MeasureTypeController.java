package com.wellbeeing.wellbeeing.api.telemedic;

import com.wellbeeing.wellbeeing.domain.telemedic.MeasureType;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureTypeServiceApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MeasureTypeController {
    private MeasureTypeServiceApi measureTypeService;

    public MeasureTypeController(@Qualifier("measureTypeService") MeasureTypeServiceApi measureTypeService){
        this.measureTypeService = measureTypeService;
    }

    @RequestMapping(path = "measure-types", method = RequestMethod.GET)
    public ResponseEntity<List<MeasureType>> getMeasureTypes(){
        List<MeasureType> measureTypesResult = measureTypeService.getMeasureTypes();
        if(measureTypesResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(measureTypesResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measure-types/{id}", method = RequestMethod.GET)
    public ResponseEntity<MeasureType> getMeasureTypeById(@PathVariable("id") UUID id){
        MeasureType measureTypeResult = measureTypeService.getMeasureTypeById(id);
        if(measureTypeResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(measureTypeResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measure-types", method = RequestMethod.POST)
    public ResponseEntity<MeasureType> addMeasureType(@RequestBody @NonNull MeasureType measureType){
        MeasureType measureTypeResult = measureTypeService.addMeasureType(measureType);
        return new ResponseEntity<>(measureTypeResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "measure-types", method = RequestMethod.PUT)
    public ResponseEntity<MeasureType> updateMeasureType(@RequestBody @NonNull MeasureType measureType){
        MeasureType measureTypeResult = measureTypeService.updateMeasureType(measureType);
        if(measureTypeResult != null)
            return new ResponseEntity<>(measureTypeResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "measure-types/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMeasureType(@PathVariable("id") UUID id){
        boolean deleteResult = measureTypeService.deleteMeasureTypeById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
