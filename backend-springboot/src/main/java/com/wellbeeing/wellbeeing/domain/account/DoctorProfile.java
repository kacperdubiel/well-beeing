package com.wellbeeing.wellbeeing.domain.account;

import javax.persistence.*;

@Entity
public class DoctorProfile extends SpecialistProfile {
    @Column()
    private EDoctorSpecialization specialization;

}
