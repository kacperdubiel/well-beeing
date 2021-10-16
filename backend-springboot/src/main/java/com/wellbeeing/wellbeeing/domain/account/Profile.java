package com.wellbeeing.wellbeeing.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
public class Profile {
    @Id
    @Column(name = "user_id")
    private UUID id;
    @Column
    private String profileImgPath;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ESex ESex;
    @Column
    private String description;
    @Column(nullable = false)
    private Date birthday;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User profileUser;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    private ProfileCard profileCard;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<TrainingPlan> trainingPlans = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<ActivityGoal> activityGoals = new HashSet<>();
}
