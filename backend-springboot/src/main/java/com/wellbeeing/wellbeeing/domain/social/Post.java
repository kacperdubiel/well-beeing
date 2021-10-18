package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.sport.ExerciseInTraining;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long post_id;
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

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getPostImgPath() {
        return postImgPath;
    }

    public void setPostImgPath(String postImgPath) {
        this.postImgPath = postImgPath;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public EPrivacy getPrivSettings() {
        return privSettings;
    }

    public void setPrivSettings(EPrivacy privSettings) {
        this.privSettings = privSettings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public Profile getCreator() {
        return creator;
    }

    public void setCreator(Profile creator) {
        this.creator = creator;
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
                "post_id=" + post_id +
                ", postImgPath='" + postImgPath + '\'' +
                ", postContent='" + postContent + '\'' +
                ", privSettings=" + privSettings +
                ", comments=" + comments +
                ", likes=" + likes +
                ", creator=" + creator +
                '}';
    }
}
