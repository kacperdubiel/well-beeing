package com.wellbeeing.wellbeeing.repository.account;

import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("profileDAO")
public interface ProfileDAO extends JpaRepository<Profile, UUID> {
    Optional<Profile> findProfileByProfileUserId(UUID userId);
    @Query( "select p from Profile p " +
            "where p.doctorProfile is not null " +
            "and :docSpec MEMBER OF p.doctorProfile.specializations " +
            "and lower(concat(p.firstName, ' ', p.lastName)) like lower(concat('%',:nameLike,'%')) ")
    Page<Profile> findDoctorsBySpecialization(@Param("docSpec") DoctorSpecialization specialization,
                                              @Param("nameLike") String like, Pageable pageable);
}
