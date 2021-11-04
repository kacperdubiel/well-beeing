package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("doctorSpecializationDAO")
public interface DoctorSpecializationDAO extends JpaRepository<DoctorSpecialization, UUID> {
    List<DoctorSpecialization> findAllByOrderByNameDesc();
}
