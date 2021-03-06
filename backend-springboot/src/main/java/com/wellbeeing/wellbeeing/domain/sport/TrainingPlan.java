package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.social.Post;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainingPlanId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator")
    private Profile creator;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Profile owner;

    //ALWAYS MONDAY
    @Column(name = "beginningDate")
    private Date beginningDate;

    @Column(name = "details")
    private String details;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EPlanStatus planStatus = EPlanStatus.SCRATCH;

    @Transient
    private int caloriesBurned;

    @OneToMany(mappedBy = "trainingPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TrainingPosition> trainingPositions = new HashSet<>();

    @OneToOne(targetEntity = TrainingPlanRequest.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "request")
    @JsonIgnore
    private TrainingPlanRequest request;
    @Transient
    private long requestId;

    @JsonIgnore
    @OneToMany(mappedBy = "originalTrainingPlan", cascade = CascadeType.ALL)
    private Set<Post> sharingPosts = new HashSet<>();

    @PostLoad
    public void PostLoad() {
        if (request != null)
            requestId = getRequest().getId();
    }


}
