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
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    @Column
    private String comContent;
    @Column
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commenter_id")
    private Profile commenter;

    public Comment(@JsonProperty("comContent") String comContent, @JsonProperty("addedDate") Date addedDate,
                   @JsonProperty("post") Post post, @JsonProperty("commenter") Profile commenter) {
        this.comContent = comContent;
        this.addedDate = addedDate;
        this.post = post;
        this.commenter = commenter;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + commentId +
                ", comContent='" + comContent + '\'' +
                ", addedDate=" + addedDate +
                ", post=" + post +
                ", commenter=" + commenter +
                '}';
    }
}
