package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @Column(name = "user_id")
    private UUID id;
    @Column
    private String profileImgPath;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column
    private ESex ESex;
    @Column
    private String description;
    @Column
    private Date birthday;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User profileUser;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    private ProfileCard profileCard;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<TrainingPlan> trainingPlans = new HashSet<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<ActivityGoal> activityGoals = new HashSet<>();
}
