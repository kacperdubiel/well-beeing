package com.wellbeeing.wellbeeing.domain.account;
import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class SpecialistProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private Profile userProfile;
}
