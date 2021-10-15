package com.wellbeeing.wellbeeing.domain.account;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class Profile {
    private String profileImgPath;
    private String firstName;
    private String lastName;
    private String description;
    private Date birthday;

    private User profileUser;
    private ProfileCard profileCard;
}
