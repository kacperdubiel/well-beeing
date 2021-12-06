package com.wellbeeing.wellbeeing.domain.social;

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
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long opinionId;
    @Column
    private String opinionContent;
    @Column
    private int rating;

    @Column
    private Date addedDate = new Date();
    @Column
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giver_id")
    private Profile giver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    private Profile receiver;
}
