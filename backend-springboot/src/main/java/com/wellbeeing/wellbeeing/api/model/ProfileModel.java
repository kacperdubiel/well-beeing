package com.wellbeeing.wellbeeing.api.model;

import com.wellbeeing.wellbeeing.domain.account.ESex;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
    private UUID id;
    private String profileImgPath;
    private String firstName;
    private String lastName;
    private ESex eSex;
    private String description;
    private Date birthday;
}
