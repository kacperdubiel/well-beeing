package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.telemedic.Measure;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public abstract class Profile {
    private String profileImgPath;
    private String firstName;
    private String lastName;
    private String description;
    private Date birthday;

    private User profileUser;
    private ProfileCard profileCard;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Measure> measures;
}
