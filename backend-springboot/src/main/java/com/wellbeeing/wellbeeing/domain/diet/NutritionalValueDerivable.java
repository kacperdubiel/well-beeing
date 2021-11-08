package com.wellbeeing.wellbeeing.domain.diet;

public interface NutritionalValueDerivable {
    double countCalories();
    double countCarbohydrates();
    double countProteins();
    double countFats();
    void setDerived();
}
