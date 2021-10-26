package com.wellbeeing.wellbeeing.domain.account;

public enum EDoctorSpecialization {
    CARDIOLOGIST ("Cardiologist"),
    DENTIST ("Dentist"),
    DERMATOLOGIST ("Dermatologist"),
    GYNECOLOGIST ("Gynecologist"),
    INTERNIST ("Internist"),
    OCULIST ("Oculist"),
    ORTHOPAEDIST ("Orthopaedist"),
    PSYCHOLOGIST ("Psychologist");

    private final String name;

    EDoctorSpecialization(String specializationName) {
        this.name = specializationName;
    }

    public String getName() {
        return name;
    }
}
