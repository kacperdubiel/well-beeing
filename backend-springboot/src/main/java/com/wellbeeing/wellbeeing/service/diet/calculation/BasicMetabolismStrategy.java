package com.wellbeeing.wellbeeing.service.diet.calculation;
import com.wellbeeing.wellbeeing.domain.account.ProfileCard;

interface BasicMetabolismStrategy {
    double calculateBasicMetabolism(ProfileCard profileCard);
}

