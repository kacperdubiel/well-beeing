package com.wellbeeing.wellbeeing.domain.diet;


public enum  EGlycemicIndexLevel {
        LOW(1, 55),
        MEDIUM(56, 69),
        HIGH(70, Integer.MAX_VALUE)
    ;


    EGlycemicIndexLevel(int lower_range, int higher_range) {
    }
}
