package com.wellbeeing.wellbeeing.domain.diet.type;

public enum EBasicMacro {
    FATS(9),
    CARBOHYDRATES(4),
    PROTEINS(4);

    private int kcalPerGram;

    EBasicMacro(int kcalPerGram) {
        this.kcalPerGram = kcalPerGram;
    }

    public int getKcalPerGram() {
        return kcalPerGram;
    }
}
