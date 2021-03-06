package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.type.EGlycemicIndexLevel;
import com.wellbeeing.wellbeeing.domain.diet.type.EMealType;

import java.util.HashMap;

public interface GlycemicIndexCalcStrategy {
    HashMap<EMealType, EGlycemicIndexLevel> countGlycemicIndexesForMeals(ProfileCard profileCard);
}
