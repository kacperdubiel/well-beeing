package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comment_id;
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

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
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

    public Profile getCommenter() {
        return commenter;
    }

    public void setCommenter(Profile commenter) {
        this.commenter = commenter;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", comContent='" + comContent + '\'' +
                ", addedDate=" + addedDate +
                ", post=" + post +
                ", commenter=" + commenter +
                '}';
    }
}
