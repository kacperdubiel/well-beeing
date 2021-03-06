package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.diet.report.Report;
import com.wellbeeing.wellbeeing.domain.sport.*;
import lombok.*;
import com.wellbeeing.wellbeeing.domain.social.*;
import com.wellbeeing.wellbeeing.domain.sport.SportReport;
import lombok.NoArgsConstructor;

import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import org.hibernate.annotations.Formula;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;
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
    @Formula(value = " concat(first_name, ' ', last_name) ")
    private String fullName;
    @Enumerated(EnumType.STRING)
    @Column
    private ESex ESex;
    @Column
    private String description;
    @Column
    private Date birthday;
    @Enumerated(EnumType.STRING)
    @Column
    private ESportTag eSportTag = ESportTag.NONE;
    @Enumerated(EnumType.STRING)
    @Column
    private ENutritionTag eNutritionTag = ENutritionTag.NONE;
    @Column
    private LocalDateTime lastRequestTime;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User profileUser;

    @Transient
    private double opinionsAverage;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private DoctorProfile doctorProfile;
    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private TrainerProfile trainerProfile;
    @OneToOne(mappedBy="userProfile", cascade = CascadeType.ALL)
    private DieticianProfile dieticianProfile;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    private ProfileCard profileCard;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Measure> measures;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<ProfileConnection> profileConnections;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<TrainingPlan> trainingPlans = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<ActivityGoal> activityGoals = new HashSet<>();

    @OneToMany(mappedBy = "reportOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Report> dietReports = new ArrayList<>();

    @OneToMany(mappedBy = "reportOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SportReport> sportReports = new ArrayList<>();


    @OneToMany(mappedBy = "creatorProfile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NutritionPlan> nutritionPlans = new ArrayList<>();

    @OneToMany(mappedBy = "ownerProfile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NutritionPlan> suggestedNutritionPlans = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "liker", cascade = CascadeType.ALL)
    private Set<Like> profileLikes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL)
    private Set<Comment> profileComments = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<Post> profilePosts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    private Set<Follow> followers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private Set<Follow> followings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "giver", cascade = CascadeType.ALL)
    private Set<Opinion> profileGivenOpinions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Opinion> profileReceivedOpinions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "submitter", cascade = CascadeType.ALL)
    private Set<RoleRequest> profileRoleRequests = new HashSet<>();

    public Profile(String firstName, String lastName, Date birthday, User profileUser) {
        System.out.println("Entered constructor");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.profileUser = profileUser;
        this.ESex = com.wellbeeing.wellbeeing.domain.account.ESex.MAN;
        profileUser.setProfile(this);
        this.id = profileUser.getId();
        System.out.println("Escaped constructor");
    }

    @PostLoad
    private void countOpinionAverage(){
        this.opinionsAverage = BigDecimal.valueOf(this.profileReceivedOpinions.stream().filter(o -> !o.isDeleted()).mapToInt(Opinion::getRating).average().orElse(0))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

    }
}
