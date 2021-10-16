package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.EGlycemicIndexLevel;
import com.wellbeeing.wellbeeing.domain.diet.EMealType;

import java.util.HashMap;

public interface GlycemicIndexCalcStrategy {
    public HashMap<EMealType, EGlycemicIndexLevel> countGlycemicIndexesForMeals(ProfileCard profileCard);
}
