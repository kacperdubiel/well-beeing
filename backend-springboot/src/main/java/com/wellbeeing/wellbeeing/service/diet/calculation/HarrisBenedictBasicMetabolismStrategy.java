package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ESex;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;

public class HarrisBenedictBasicMetabolismStrategy implements BasicMetabolismStrategy {

    @Override
    public double calculateBasicMetabolism(ProfileCard profileCard) {
        if(profileCard.getESex() == ESex.WOMAN)
            return 655.1 + (9.567 * profileCard.getWeight()) + (1.85 * profileCard.getHeight()) - (4.68 * profileCard.getAge());
        else
            return 66.47 + (13.7 * profileCard.getWeight()) + (5 * profileCard.getHeight()) - (6.76 * profileCard.getAge());
    }
}
