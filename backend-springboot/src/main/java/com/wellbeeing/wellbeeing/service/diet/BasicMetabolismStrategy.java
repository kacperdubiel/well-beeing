package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.account.ESex;

interface BasicMetabolismStrategy {
    double calculateBasicMetabolism(int age, ESex sex, double weight, double height);
}

