package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.ESex;

public class MifflinBasicMetabolismStrategy implements BasicMetabolismStrategy {
    @Override
    public double calculateBasicMetabolism(int age, ESex sex, double weight, double height) {
        if(sex == ESex.WOMAN)
            return (10 * weight) + (6.25 * height) - (5 * age) - 161;
        else
            return (10 * weight) + (6.25 * height) - (5 * age) + 5;
    }
}
