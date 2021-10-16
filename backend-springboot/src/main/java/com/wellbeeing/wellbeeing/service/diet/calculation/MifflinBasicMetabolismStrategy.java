package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ESex;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;

public class MifflinBasicMetabolismStrategy implements BasicMetabolismStrategy {
    @Override
    public double calculateBasicMetabolism(ProfileCard profileCard) {
        if(profileCard.getESex() == ESex.WOMAN)
            return (10 * profileCard.getWeight()) + (6.25 *profileCard.getHeight()) - (5 * profileCard.getAge()) - 161;
        else
            return (10 * profileCard.getWeight()) + (6.25 * profileCard.getHeight()) - (5 * profileCard.getAge()) + 5;
    }
}
