package com.wellbeeing.wellbeeing.domain.diet;

public interface NutritionalValueDerivable {
    double countCalories();
    double countCarbohydrates();
    double countProteins();
    double countFats();
    double countFiber();
    double countSalt();
    double countCholesterol();
    double countCaffeine();
    double countSugar();
    double countSaturatedFats();
    void setDerived();
}
