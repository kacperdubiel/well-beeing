package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.EActivityLevel;

public interface CompleteMetabolismStrategy {
    double calculateCompleteMetabolism(double basicMetabolism, ProfileCard profileCard);
}
