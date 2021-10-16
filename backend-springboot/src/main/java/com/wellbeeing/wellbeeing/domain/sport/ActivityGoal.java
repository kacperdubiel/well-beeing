package com.wellbeeing.wellbeeing.domain.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@Entity
public class ActivityGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long activityGoal_id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "goal_type", nullable = false)
    private EGoalType goalType;

    @Column(name = "numeric_value")
    private float numericValue;

    @Column(name = "text_value")
    private String textValue;

    @Column(name = "goal_creation_date", nullable = false)
    private Date goalCreationDate = new Date();

    @Column(name = "goal_target_date")
    private Date goalTargetDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Profile owner;

    public ActivityGoal(EGoalType goalType, float numericValue, String textValue, Date goalTargetDate, Profile owner) {
        this.goalType = goalType;
        this.numericValue = numericValue;
        this.textValue = textValue;
        this.goalTargetDate = goalTargetDate;
        this.owner = owner;
    }

    public long getActivityGoal_id() {
        return activityGoal_id;
    }

    public void setActivityGoal_id(long activityGoal_id) {
        this.activityGoal_id = activityGoal_id;
    }

    public EGoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(EGoalType goalType) {
        this.goalType = goalType;
    }

    public float getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(float numericValue) {
        this.numericValue = numericValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Date getGoalCreationDate() {
        return goalCreationDate;
    }

    public void setGoalCreationDate(Date goalCreationDate) {
        this.goalCreationDate = goalCreationDate;
    }

    public Date getGoalTargetDate() {
        return goalTargetDate;
    }

    public void setGoalTargetDate(Date goalTargetDate) {
        this.goalTargetDate = goalTargetDate;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }
}
