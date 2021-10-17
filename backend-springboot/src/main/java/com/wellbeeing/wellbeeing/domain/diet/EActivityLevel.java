package com.wellbeeing.wellbeeing.domain.diet;

public enum EActivityLevel {
    VERY_LOW(1.2),
    LOW(1.4),
    MEDIUM(1.5),
    HIGH(1.7),
    VERY_HIGH(1.8)
    ;

    private double palFactor;

    EActivityLevel(double palFactor) {
        this.palFactor = palFactor;
    }

    public double getPalFactor() {
        return palFactor;
    }
}
