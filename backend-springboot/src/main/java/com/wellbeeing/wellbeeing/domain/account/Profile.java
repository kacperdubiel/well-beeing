package com.wellbeeing.wellbeeing.domain.account;

import lombok.*;
import com.wellbeeing.wellbeeing.domain.social.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.NoArgsConstructor;

import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    @Column
    private ESex ESex;
    @Column
    private String description;
    @Column
    private Date birthday;
    @Enumerated(EnumType.STRING)
    @Column
    private ESportTag eSportTag;
    @Enumerated(EnumType.STRING)
    @Column
    private ENutritionTag eNutritionTag;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User profileUser;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    //@JsonIgnore
    private ProfileCard profileCard;

    @OneToOne(mappedBy="profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private Settings settings;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "liker", cascade = CascadeType.ALL)
    private Set<Like> profileLikes = new HashSet<>();

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL)
    private Set<Comment> profileComments = new HashSet<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Set<Post> profilePosts = new HashSet<>();

    @OneToMany(mappedBy = "giver", cascade = CascadeType.ALL)
    private Set<Opinion> profileGivenOpinions = new HashSet<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Opinion> profileReceivedOpinions = new HashSet<>();

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


    public Profile(String firstName, String lastName, Date birthday, ESportTag eSportTag, ENutritionTag eNutritionTag, User profileUser) {
        System.out.println("Entered constructor");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.eSportTag = eSportTag;
        this.eNutritionTag = eNutritionTag;
        this.profileUser = profileUser;
        this.ESex = com.wellbeeing.wellbeeing.domain.account.ESex.MAN;
        profileUser.setProfile(this);
        this.id = profileUser.getId();
        System.out.println("Escaped constructor");
    }
}
