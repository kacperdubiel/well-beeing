package com.wellbeeing.wellbeeing.domain.diet.type;

public enum EDetailedMacro {
    VEGETABLE_PROTEINS(EBasicMacro.PROTEINS, "VEGETABLE"),
    ANIMAL_PROTEINS(EBasicMacro.PROTEINS, "ANIMAL"),
    SATURATED_FATS(EBasicMacro.FATS, "SATURATED"),
    POLYUNSATURATED_FATS(EBasicMacro.FATS, "POLYUNSATURATED"),
    MONOUNSATURATED_FATS(EBasicMacro.FATS, "MONOUNSATURATED"),
    SUGAR_CARBOHYDRATES(EBasicMacro.CARBOHYDRATES, "SUGAR");

    private EBasicMacro basicMacro;
    private String detail;

    EDetailedMacro(EBasicMacro basicMacro, String detail) {
    }

    public EBasicMacro getBasicMacro() {
        return basicMacro;
    }

    public String getDetail() {
        return detail;
    }
}