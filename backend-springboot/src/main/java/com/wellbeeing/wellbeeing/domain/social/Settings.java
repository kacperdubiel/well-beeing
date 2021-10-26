package com.wellbeeing.wellbeeing.domain.social;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.sport.EExerciseType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Settings {
    @Id
    @Column(name = "profile_id")
    private UUID id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "profile_id", referencedColumnName = "user_id")
    private Profile profile;
    @Column(nullable = false)
    private EPrivacy defTrainPriv;
    @Column(nullable = false)
    private EPrivacy defDietPriv;
    @Column(nullable = false)
    private EPrivacy defPostPriv;
    @Column(nullable = false)
    private EPrivacy friendsListPriv;
    @Column(nullable = false)
    private EPrivacy followListPriv;
}
