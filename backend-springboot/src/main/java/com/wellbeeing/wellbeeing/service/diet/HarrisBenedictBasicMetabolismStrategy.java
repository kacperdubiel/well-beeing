package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.ESex;

public class HarrisBenedictBasicMetabolismStrategy implements BasicMetabolismStrategy {

    @Override
    public double calculateBasicMetabolism(int age, ESex sex, double weight, double height) {
        if(sex == ESex.WOMAN)
            return 655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age);
        else
            return 66.47 + (13.7 * weight) + (5 * height) - (6.76 * age);
    }
}
