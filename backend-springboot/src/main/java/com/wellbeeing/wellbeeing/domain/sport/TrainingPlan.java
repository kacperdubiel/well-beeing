package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.account.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingPlanId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private Profile creator;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Profile owner;

    //ALWAYS MONDAY
    @Column(name = "beginningDate")
    private Date beginningDate;

    @Column(name = "details")
    private String details;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EPlanStatus planStatus = EPlanStatus.SCRATCH;

    @Transient
    private int caloriesBurned;

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL)
    private Set<TrainingPosition> trainingPositions = new HashSet<>();

    public TrainingPlan(Profile owner, int year, int week, String details) {
        this.owner = owner;
        this.details = details;
    }

    public TrainingPlan(Profile owner, int year, int week, String details, Profile creator) {
        this.owner = owner;
        this.details = details;
        this.creator = creator;
    }


}
