package com.wellbeeing.wellbeeing.api.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorProfile;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.service.account.DoctorSpecializationService;
import com.wellbeeing.wellbeeing.service.account.ProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DoctorSpecializationController {
    private DoctorSpecializationService doctorSpecializationService;
    private ProfileService profileService;

    public DoctorSpecializationController(@Qualifier("doctorSpecializationService") DoctorSpecializationService docSpecService,
                                          @Qualifier("profileService") ProfileService profileService){
        this.doctorSpecializationService = docSpecService;
        this.profileService = profileService;
    }

    @RequestMapping(path = "doctor-specializations", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorSpecializations(){
        List<DoctorSpecialization> docSpecResult = doctorSpecializationService.getDoctorSpecializations();
        return new ResponseEntity<>(docSpecResult, HttpStatus.OK);
    }

    @RequestMapping(path = "doctor-specializations/{id}", method = RequestMethod.GET)
    public ResponseEntity<DoctorSpecialization> getDoctorSpecializationById(@PathVariable("id") UUID id)
            throws NotFoundException
    {
        DoctorSpecialization docSpecResult = doctorSpecializationService.getDoctorSpecializationById(id);
        return new ResponseEntity<>(docSpecResult, HttpStatus.OK);
    }

    @RequestMapping(path = "doctor-specializations", method = RequestMethod.POST)
    public ResponseEntity<DoctorSpecialization> addDoctorSpecialization(@RequestBody @NonNull DoctorSpecialization doctorSpecialization)
            throws ConflictException
    {
        DoctorSpecialization docSpecResult = doctorSpecializationService.addDoctorSpecialization(doctorSpecialization);
        return new ResponseEntity<>(docSpecResult, HttpStatus.CREATED);
    }

    @RequestMapping(path = "doctor-specializations", method = RequestMethod.PUT)
    public ResponseEntity<DoctorSpecialization> updateDoctorSpecialization(@RequestBody @NonNull DoctorSpecialization doctorSpecialization)
            throws NotFoundException
    {
        DoctorSpecialization docSpecResult = doctorSpecializationService.updateDoctorSpecialization(doctorSpecialization);
        return new ResponseEntity<>(docSpecResult, HttpStatus.OK);
    }

    @RequestMapping(path = "doctor-specializations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteDoctorSpecialization(@PathVariable("id") UUID id) throws NotFoundException {
        doctorSpecializationService.deleteDoctorSpecializationById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(path = "doctors/{doctor_id}/doctor-specializations", method = RequestMethod.POST)
    public ResponseEntity<?> addDoctorSpecializationToDoctor(@PathVariable("doctor_id") UUID doctorId,
                                                             @RequestBody @NonNull DoctorSpecialization doctorSpecialization)
            throws NotFoundException
    {
        DoctorProfile doctorProfileResult = profileService.addDoctorSpecializationToDoctor(doctorId, doctorSpecialization.getId());

        return new ResponseEntity<>(doctorProfileResult, HttpStatus.OK);
    }

}
