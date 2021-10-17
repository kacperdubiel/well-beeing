package com.wellbeeing.wellbeeing.domain.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.TrainerProfile;
import com.wellbeeing.wellbeeing.domain.account.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingPlan_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private TrainerProfile creator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Profile owner;

    @Column(name = "year")
    private int year;

    @Column(name = "week")
    private int week;

    @Column(name = "details")
    private String details;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EPlanStatus planStatus = EPlanStatus.SCRATCH;

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL)
    private Set<TrainingPosition> trainingPositions = new HashSet<>();

    public TrainingPlan () {

    }

    public TrainingPlan(Profile owner, int year, int week, String details) {
        this.owner = owner;
        this.year = year;
        this.week = week;
        this.details = details;
    }

    public TrainingPlan(Profile owner, int year, int week, String details, TrainerProfile creator) {
        this.owner = owner;
        this.year = year;
        this.week = week;
        this.details = details;
        this.creator = creator;
    }

    public long getTrainingPlan_id() {
        return trainingPlan_id;
    }

    public void setTrainingPlan_id(long trainingPlan_id) {
        this.trainingPlan_id = trainingPlan_id;
    }
}
