package com.wellbeeing.wellbeeing.domain.sport;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.account.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
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
    private double numericValue;

    @Column(name = "text_value")
    private String textValue;

    @Column(name = "goal_creation_date", nullable = false)
    private Date goalCreationDate = new Date();

    @Column(name = "goal_target_date")
    private Date goalTargetDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Profile owner;

    public ActivityGoal(EGoalType goalType, double numericValue, String textValue, Date goalTargetDate, Profile owner) {
        this.goalType = goalType;
        this.numericValue = numericValue;
        this.textValue = textValue;
        this.goalTargetDate = goalTargetDate;
        this.owner = owner;
    }


}
