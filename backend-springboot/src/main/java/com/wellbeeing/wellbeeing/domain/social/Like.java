package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Table(name = "likes")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeId;
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

    @Override
    public String toString() {
        return "Like{" +
                "like_id=" + likeId +
                ", addedDate=" + addedDate +
                ", post=" + post +
                ", liker=" + liker +
                '}';
    }
}
