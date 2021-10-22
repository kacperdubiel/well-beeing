package com.wellbeeing.wellbeeing.api.telemedic;


import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import com.wellbeeing.wellbeeing.service.telemedic.MeasureServiceApi;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MeasureController {
    private MeasureServiceApi measureService;
    private ProfileService profileService;

    public MeasureController(@Qualifier("measureService") MeasureServiceApi measureService,
                             @Qualifier("profileService") ProfileService profileService){
        this.measureService = measureService;
    }

    @RequestMapping(path = "measures/{id}", method = RequestMethod.GET)
    public ResponseEntity<Measure> getMeasureById(@PathVariable("id") UUID id){
        Measure measureResult = measureService.getMeasureById(id);
        if(measureResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(measureResult, HttpStatus.OK);
    }

    @RequestMapping(path = "measures/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Measure>> getUserMeasures(@PathVariable("id") UUID profileId){
        Profile measuresOwner = null;
        try {
            measuresOwner = profileService.getProfileById(profileId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        List<Measure> measuresResult = measureService.getMeasuresByProfile(measuresOwner);
        if(measuresResult == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            // Sorted by measure date DESC
            measuresResult.sort((Measure m1, Measure m2) -> (-1*(m1.getMeasureDate().compareTo(m2.getMeasureDate()))));
            return new ResponseEntity<>(measuresResult, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "measures", method = RequestMethod.POST)
    public ResponseEntity<Measure> addMeasure(@RequestBody @NonNull Measure measure){
        Measure measureResult = measureService.addMeasure(measure);
        return new ResponseEntity<>(measureResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "measures", method = RequestMethod.PUT)
    public ResponseEntity<Measure> updateMeasure(@RequestBody @NonNull Measure measure){
        Measure measureResult = measureService.updateMeasure(measure);
        if(measureResult != null)
            return new ResponseEntity<>(measureResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "measures/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteMeasure(@PathVariable("id") UUID id){
        boolean deleteResult = measureService.deleteMeasureById(id);
        if(deleteResult)
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
