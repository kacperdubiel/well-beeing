package com.wellbeeing.wellbeeing.domain.sport;

import com.wellbeeing.wellbeeing.domain.User;
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

    @Column(name = "creator")
    private Trainer creator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;

    @Column(name = "year")
    private int year;

    @Column(name = "week")
    private int week;

    @Column(name = "details")
    private String details;

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL)
    private Set<TrainingPosition> trainingPositions = new HashSet<>();

    public TrainingPlan () {

    }

    public TrainingPlan(User owner, int year, int week, String details) {
        this.owner = owner;
        this.year = year;
        this.week = week;
        this.details = details;
    }

    public long getTrainingPlan_id() {
        return trainingPlan_id;
    }

    public void setTrainingPlan_id(long trainingPlan_id) {
        this.trainingPlan_id = trainingPlan_id;
    }
}
