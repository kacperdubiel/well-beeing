package com.wellbeeing.wellbeeing.domain.diet;

public enum EBasicMacros {
    FATS(9),
    CARBOHYDRATES(4),
    PROTEINS(4);

    private int kcalPerGram;

    EBasicMacros(int kcalPerGram) {
    }

    public int getKcalPerGram() {
        return kcalPerGram;
    }
}
