package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followId;
    @Column
    private Date addedDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
    private Profile follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followed_id")
    private Profile followed;

    public Follow(@JsonProperty("follower") Profile follower,
                  @JsonProperty("followed") Profile followed) {
        this.follower = follower;
        this.followed = followed;
    }
}
