package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.EBasicMacros;

import java.util.HashMap;

public interface MacrosCalcStrategy {
    HashMap<EBasicMacros, Double> calculateMacros(double completeMetabolism, ProfileCard profileCard);
}
