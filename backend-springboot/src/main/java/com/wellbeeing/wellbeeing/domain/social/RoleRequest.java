package com.wellbeeing.wellbeeing.domain.social;

import com.wellbeeing.wellbeeing.domain.account.ERole;
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
public class RoleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleReqId;

    @Column
    private Date addedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitter_id")
    private Profile submitter;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole role;

    @Column(unique = true)
    private String documentImgPath;

    @Enumerated(EnumType.STRING)
    @Column
    private EStatus status;

    @Column
    private String comment;
}
