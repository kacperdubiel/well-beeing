package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.type.EBasicMacro;

import java.util.HashMap;

public interface MacrosCalcStrategy {
    HashMap<EBasicMacro, Double> calculateMacros(double completeMetabolism, ProfileCard profileCard);
}
