package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @Column(unique = true)//idk
    private String postImgPath;
    @Column
    private String postContent;
    @Enumerated(EnumType.STRING)
    @Column
    private EPrivacy privSettings;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Profile creator;

    public Post(@JsonProperty("postImgPath") String postImgPath, @JsonProperty("postContent") String postContent,
                @JsonProperty("privSettings") EPrivacy privSettings, @JsonProperty("creator") Profile creator) {
        this.postImgPath = postImgPath;
        this.postContent = postContent;
        this.privSettings = privSettings;
        this.creator = creator;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addLikeToPost(Like like) {
        this.likes.add(like);
    }

    public void addCommentToPost(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + postId +
                ", postImgPath='" + postImgPath + '\'' +
                ", postContent='" + postContent + '\'' +
                ", privSettings=" + privSettings +
                ", comments=" + comments +
                ", likes=" + likes +
                ", creator=" + creator +
                '}';
    }
}
