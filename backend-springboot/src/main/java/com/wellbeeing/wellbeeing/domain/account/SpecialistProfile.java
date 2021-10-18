package com.wellbeeing.wellbeeing.domain.account;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
@NoArgsConstructor
@MappedSuperclass
public abstract class SpecialistProfile {
    @Id
    @Column(name = "user_id")
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Profile userProfile;

    public SpecialistProfile (Profile userProfile) {
        this.id = userProfile.getId();
    }
}
