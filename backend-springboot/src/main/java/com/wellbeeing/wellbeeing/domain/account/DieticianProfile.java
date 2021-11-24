package com.wellbeeing.wellbeeing.domain.account;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class DieticianProfile extends SpecialistProfile {

    public DieticianProfile(Profile userProfile) {
        super(userProfile);
    }
}
