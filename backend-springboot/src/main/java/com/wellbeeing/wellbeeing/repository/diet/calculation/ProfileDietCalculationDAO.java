package com.wellbeeing.wellbeeing.repository.diet.calculation;

import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("profileDietCalculationDAO")
public interface ProfileDietCalculationDAO extends JpaRepository<ProfileDietCalculation, UUID> {
}
