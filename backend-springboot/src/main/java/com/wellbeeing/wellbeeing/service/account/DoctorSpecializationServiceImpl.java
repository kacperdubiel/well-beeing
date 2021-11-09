package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.repository.account.DoctorSpecializationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("doctorSpecializationService")
public class DoctorSpecializationServiceImpl implements DoctorSpecializationService {
    private DoctorSpecializationDAO doctorSpecializationDAO;

    @Autowired
    public DoctorSpecializationServiceImpl(@Qualifier("doctorSpecializationDAO") DoctorSpecializationDAO doctorSpecializationDAO){
        this.doctorSpecializationDAO = doctorSpecializationDAO;
    }

    @Override
    public List<DoctorSpecialization> getDoctorSpecializations(){
        return doctorSpecializationDAO.findAllByOrderByNameDesc();
    }

    @Override
    public DoctorSpecialization getDoctorSpecializationById(UUID doctorSpecializationId) throws NotFoundException {
        DoctorSpecialization doctorSpecialization = doctorSpecializationDAO.findById(doctorSpecializationId).orElse(null);
        if(doctorSpecialization == null) {
            throw new NotFoundException("Doctor specialization with id: " + doctorSpecializationId +" not found!");
        }
        return doctorSpecialization;
    }

    @Override
    public DoctorSpecialization addDoctorSpecialization(DoctorSpecialization doctorSpecialization) throws ConflictException {
        UUID doctorSpecializationId = doctorSpecialization.getId();
        if(doctorSpecializationId != null) {
            DoctorSpecialization doctorSpecializationResult = doctorSpecializationDAO.findById(doctorSpecializationId).orElse(null);
            if (doctorSpecializationResult != null) {
                throw new ConflictException("Doctor specialization with id: " + doctorSpecializationId + " already exists!");
            }
        }

        return doctorSpecializationDAO.save(doctorSpecialization);
    }

    @Override
    public DoctorSpecialization updateDoctorSpecialization(DoctorSpecialization updatedDoctorSpecialization) throws NotFoundException {
        UUID doctorSpecializationId = updatedDoctorSpecialization.getId();

        if(doctorSpecializationId == null) {
            throw new NotFoundException("Specify doctor specialization id!");
        }

        DoctorSpecialization doctorSpecializationResult = doctorSpecializationDAO.findById(doctorSpecializationId).orElse(null);
        if(doctorSpecializationResult == null) {
            throw new NotFoundException("Doctor specialization with id: " + doctorSpecializationId + " not found!");
        }

        return doctorSpecializationDAO.save(updatedDoctorSpecialization);
    }

    @Override
    public void deleteDoctorSpecializationById(UUID doctorSpecializationId) throws NotFoundException {
        DoctorSpecialization doctorSpecializationResult = doctorSpecializationDAO.findById(doctorSpecializationId).orElse(null);
        if(doctorSpecializationResult == null) {
            throw new NotFoundException("Doctor specialization with id: " + doctorSpecializationId + " not found!");
        }

        doctorSpecializationDAO.deleteById(doctorSpecializationId);
    }
}
