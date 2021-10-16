package com.wellbeeing.wellbeeing.domain.diet;


public enum  EGlycemicIndexLevel {
        LOW(1, 55),
        MEDIUM(56, 69),
        HIGH(70, Integer.MAX_VALUE),
        ANY_RECOMMENDED(1, 69),
        ANY(1, Integer.MAX_VALUE );

    private int lowerRange;
    private int higherRange;

    EGlycemicIndexLevel(int lower_range, int higher_range) {
        this.lowerRange = lower_range;
        this.higherRange = higher_range;
    }
}
