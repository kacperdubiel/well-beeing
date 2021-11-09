package com.wellbeeing.wellbeeing.domain.account;

import javax.persistence.*;
import java.util.List;

@Entity
public class DoctorProfile extends SpecialistProfile {
    @ManyToMany
    @JoinTable(
            name = "doctors_specializations",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    )
    private List<DoctorSpecialization> specializations;

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
