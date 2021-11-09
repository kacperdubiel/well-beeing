package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("doctorProfileDAO")
public interface DoctorProfileDAO extends JpaRepository<DoctorProfile, UUID> {
}
