package com.wellbeeing.wellbeeing.domain.account;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class DoctorProfile extends SpecialistProfile {
    @ManyToMany
    @JoinTable(
            name = "doctors_specializations",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    )
    private List<DoctorSpecialization> specializations = new ArrayList<>();

    public DoctorProfile(Profile userProfile) {
        super(userProfile);
    }

    public List<DoctorSpecialization> getSpecializations() {
        return specializations;
    }

    public void addDoctorSpecialization(DoctorSpecialization docSpec) {
        this.specializations.add(docSpec);
    }

    public void setSpecializations(List<DoctorSpecialization> specializations) {
        this.specializations = specializations;
    }
}
