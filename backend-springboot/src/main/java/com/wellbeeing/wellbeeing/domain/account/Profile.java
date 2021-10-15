package com.wellbeeing.wellbeeing.domain.account;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public abstract class Profile {
    private String profileImgPath;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private ESex ESex;
    @Column
    private String description;
    @Column(nullable = false)
    private Date birthday;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User profileUser;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    private ProfileCard profileCard;
}
