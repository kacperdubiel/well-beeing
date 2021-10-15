package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.EActivityLevel;

public interface CompleteMetabolismStrategy {
    double calculateCompleteMetabolism(double basicMetabolism, EActivityLevel activityLevel, int activeMinutesPerWeek);
}
