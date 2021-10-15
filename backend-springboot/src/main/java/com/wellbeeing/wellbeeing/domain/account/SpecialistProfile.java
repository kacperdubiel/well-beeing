package com.wellbeeing.wellbeeing.domain.account;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SpecialistProfile {
    private Profile userProfile;
}
