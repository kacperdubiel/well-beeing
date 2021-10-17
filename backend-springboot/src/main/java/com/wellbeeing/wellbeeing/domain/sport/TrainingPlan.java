package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private Profile creator;
    @JsonIgnore
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

    public TrainingPlan(Profile owner, int year, int week, String details, Profile creator) {
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

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public EPlanStatus getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(EPlanStatus planStatus) {
        this.planStatus = planStatus;
    }

    public Set<TrainingPosition> getTrainingPositions() {
        return trainingPositions;
    }

    public void setTrainingPositions(Set<TrainingPosition> trainingPositions) {
        this.trainingPositions = trainingPositions;
    }
}
