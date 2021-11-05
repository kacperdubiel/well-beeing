package com.wellbeeing.wellbeeing.domain.account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
public abstract class SpecialistProfile {
    @Id
    @Column(name = "user_id")
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Profile userProfile;

    public SpecialistProfile (Profile userProfile) {
        this.id = userProfile.getId();
    }
}
