package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
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
    @Column
    private String postContent;
    @Enumerated(EnumType.STRING)
    @Column
    private EPrivacy privSettings = EPrivacy.FOR_EVERYONE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private Profile creator;

    @Column
    private boolean isSharing = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "original_post_id")
    private Post originalPost = null;
    @JsonIgnore
    @OneToMany(mappedBy = "originalPost", cascade = CascadeType.ALL)
    private Set<Post> sharingPosts = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public void addLikeToPost(Like like) {
        this.likes.add(like);
    }

    public void addCommentToPost(Comment comment) {
        this.comments.add(comment);
    }

}
