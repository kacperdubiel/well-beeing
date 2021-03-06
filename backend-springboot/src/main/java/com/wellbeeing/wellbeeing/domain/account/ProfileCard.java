package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.calculation.ProfileDietCalculation;
import com.wellbeeing.wellbeeing.domain.diet.type.EActivityLevel;
import com.wellbeeing.wellbeeing.domain.diet.type.EDietGoal;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private double height;
    @Column
    private double weight;
    @Column
    private int age;
    @Enumerated(EnumType.STRING)
    @Column
    private ESex ESex;
    @ManyToMany
    @JoinTable(
            name = "profile_card_ailment",
            joinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    )
    private List<Ailment> ailments;
    @Enumerated(EnumType.STRING)
    @Column
    private EActivityLevel activityLevel;
    @Column
    private int trainingActivityTimePerWeek;
    @Enumerated(EnumType.STRING)
    @Column
    private EDietGoal dietGoal;
    @OneToOne(mappedBy = "profileCard")
    @JsonIgnore
    private Profile profile;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_calculations_id", referencedColumnName = "id")
    @JsonIgnore
    private ProfileDietCalculation dietCalculations;
}



