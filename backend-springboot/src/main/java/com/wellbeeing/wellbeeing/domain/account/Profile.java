package com.wellbeeing.wellbeeing.domain.account;

import com.wellbeeing.wellbeeing.domain.sport.ActivityGoal;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.wellbeeing.wellbeeing.domain.telemedic.Measure;
import com.wellbeeing.wellbeeing.domain.telemedic.ProfileConnection;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;
@Getter
@Setter
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
    @MapsId
    private User profileUser;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    private ProfileCard profileCard;

    @OneToMany(mappedBy = "owner")
    private List<Measure> measures;

    @OneToMany(mappedBy = "profile")
    private List<ProfileConnection> profileConnections;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<TrainingPlan> trainingPlans = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<ActivityGoal> activityGoals = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

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

//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public String getProfileImgPath() {
//        return profileImgPath;
//    }
//
//    public void setProfileImgPath(String profileImgPath) {
//        this.profileImgPath = profileImgPath;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public com.wellbeeing.wellbeeing.domain.account.ESex getESex() {
//        return ESex;
//    }
//
//    public void setESex(com.wellbeeing.wellbeeing.domain.account.ESex ESex) {
//        this.ESex = ESex;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public User getProfileUser() {
//        return profileUser;
//    }
//
//    public void setProfileUser(User profileUser) {
//        this.profileUser = profileUser;
//    }
//
//    public ProfileCard getProfileCard() {
//        return profileCard;
//    }
//
//    public void setProfileCard(ProfileCard profileCard) {
//        this.profileCard = profileCard;
//    }
//
//    public List<Measure> getMeasures() {
//        return measures;
//    }
//
//    public void setMeasures(List<Measure> measures) {
//        this.measures = measures;
//    }
//
//    public List<ProfileConnection> getProfileConnections() {
//        return profileConnections;
//    }
//
//    public void setProfileConnections(List<ProfileConnection> profileConnections) {
//        this.profileConnections = profileConnections;
//    }

}
