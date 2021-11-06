package com.wellbeeing.wellbeeing.domain.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "profile_connections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileConnection {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private EConnectionType connectionType;
    @Column
    private boolean isAccepted;
    @Column
    private Date requestDate;
    @Column
    private Date acceptDate;

    @ManyToOne
    private Profile profile;
    @ManyToOne
    private Profile connectedWith;

}
