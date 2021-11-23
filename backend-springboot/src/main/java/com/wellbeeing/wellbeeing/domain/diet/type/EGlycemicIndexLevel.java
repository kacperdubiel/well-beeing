package com.wellbeeing.wellbeeing.domain.diet.type;


public enum  EGlycemicIndexLevel {
        VERY_LOW(0, 10),
        LOW(11, 30),
        MEDIUM(31, 50),
        HIGH(51, 70),
        VERY_HIGH(71, Integer.MAX_VALUE),
        ANY_RECOMMENDED(1, 69),
        ANY(1, Integer.MAX_VALUE );

    private final int lowerRange;
    private final int higherRange;

    EGlycemicIndexLevel(int lower_range, int higher_range) {
        this.lowerRange = lower_range;
        this.higherRange = higher_range;
    }

    public int getLowerRange() {
        return lowerRange;
    }

    public int getHigherRange() {
        return higherRange;
    }
}
