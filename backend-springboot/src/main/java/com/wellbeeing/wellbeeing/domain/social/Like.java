package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Table(name = "likes")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long like_id;
    @Column
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liker_id")
    private Profile liker;

    public Like(@JsonProperty("addedDate") Date addedDate, @JsonProperty("post") Post post,
                @JsonProperty("liker") Profile liker) {
        this.addedDate = addedDate;
        this.post = post;
        this.liker = liker;
    }

    public long getLike_id() {
        return like_id;
    }

    public void setLike_id(long like_id) {
        this.like_id = like_id;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Profile getLiker() {
        return liker;
    }

    public void setLiker(Profile liker) {
        this.liker = liker;
    }

    @Override
    public String toString() {
        return "Like{" +
                "like_id=" + like_id +
                ", addedDate=" + addedDate +
                ", post=" + post +
                ", liker=" + liker +
                '}';
    }
}
