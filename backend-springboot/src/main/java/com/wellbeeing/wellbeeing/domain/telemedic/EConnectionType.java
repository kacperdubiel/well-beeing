package com.wellbeeing.wellbeeing.domain.telemedic;

public enum EConnectionType {
    WITH_USER ("WITH_USER"),
    WITH_DOCTOR ("WITH_DOCTOR"),
    WITH_DIETICIAN ("WITH_DIETICIAN"),
    WITH_TRAINER ("WITH_TRAINER");

    private final String typeName;

    EConnectionType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
