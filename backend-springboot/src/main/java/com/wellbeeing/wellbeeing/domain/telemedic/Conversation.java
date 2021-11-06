package com.wellbeeing.wellbeeing.domain.telemedic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private EConnectionType connectionType;

    @Column
    private boolean isReadByFirstUser;
    @Column
    private boolean isReadBySecondUser;

    @ManyToOne
    private Profile firstProfile; // Basic_user if connectionType == WITH_{SPECIALIST}
    @ManyToOne
    private Profile secondProfile; // Specialist if connectionType == WITH_{SPECIALIST}
    @JsonIgnore
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;

}
