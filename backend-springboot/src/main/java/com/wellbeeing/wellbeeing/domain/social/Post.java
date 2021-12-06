package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.nutrition_plan.NutritionPlan;
import com.wellbeeing.wellbeeing.domain.sport.TrainingPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @Column
    private Date addedDate = new Date();
    @Column(unique = true)
    private String postImgPath;
    @Column(columnDefinition = "TEXT")
    private String postContent;
    @Enumerated(EnumType.STRING)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private Profile creator;

    @Column
    private boolean isSharing = false;

    @Column
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "original_post_id")
    private Post originalPost = null;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "original_nutrition_plan_id")
    private NutritionPlan originalNutritionPlan = null;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "original_training_plan_id")
    private TrainingPlan originalTrainingPlan = null;

    @JsonIgnore
    @OneToMany(mappedBy = "originalPost", cascade = CascadeType.ALL)
    private Set<Post> sharingPosts = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @Column
    private int sharingCounter = 0;

    public Post(String postContent, Profile creator, Post originalPost) {
        this.postImgPath = null;
        this.postContent = postContent;
        this.creator = creator;
        this.isSharing = true;
        this.originalPost = originalPost;
    }

    public Post(String postContent, Profile creator, NutritionPlan originalNutritionPlan) {
        this.postImgPath = null;
        this.postContent = postContent;
        this.creator = creator;
        this.isSharing = true;
        this.originalNutritionPlan = originalNutritionPlan;
    }

    public Post(String postContent, Profile creator, TrainingPlan originalTrainingPlan) {
        this.postImgPath = null;
        this.postContent = postContent;
        this.creator = creator;
        this.isSharing = true;
        this.originalTrainingPlan = originalTrainingPlan;
    }

    public void increaseSharingCounter() {
        this.sharingCounter++;
    }

}
