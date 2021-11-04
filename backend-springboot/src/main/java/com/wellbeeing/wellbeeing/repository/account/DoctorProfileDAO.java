package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorProfile;
import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("doctorProfileDAO")
public interface DoctorProfileDAO extends JpaRepository<DoctorProfile, UUID> {
    @Query( "select dp from DoctorProfile dp where ?1 MEMBER OF dp.specializations and lower(concat(dp.userProfile.firstName, ' ', dp.userProfile.lastName)) like lower(concat('%',?2,'%')) ")
    Page<DoctorProfile> findBySpecialization(DoctorSpecialization specialization, String like, Pageable pageable);


}
