package com.wellbeeing.wellbeeing.service.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface DoctorSpecializationService {
    List<DoctorSpecialization> getDoctorSpecializations();
    DoctorSpecialization getDoctorSpecializationById(UUID doctorSpecializationId) throws NotFoundException;
    DoctorSpecialization addDoctorSpecialization(DoctorSpecialization doctorSpecialization) throws ConflictException;
    DoctorSpecialization updateDoctorSpecialization(DoctorSpecialization updatedDoctorSpecialization) throws NotFoundException;
    void deleteDoctorSpecializationById(UUID measureTypeId) throws NotFoundException;
}
