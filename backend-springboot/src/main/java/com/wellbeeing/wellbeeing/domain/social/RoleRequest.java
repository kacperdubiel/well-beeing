package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.ERole;
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
public class RoleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleReqId;

    @Column
    private Date addedDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submitter_id")
    private Profile submitter;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole role;

    @Column(unique = true)
    private String documentImgPath;

    @Enumerated(EnumType.STRING)
    @Column
    private EStatus status = EStatus.PENDING;

    @Column
    private String comment ="";

    @Override
    public String toString() {
        return "RoleRequest{" +
                "roleReqId=" + roleReqId +
                ", addedDate=" + addedDate +
                ", submitter=" + submitter +
                ", role=" + role +
                ", documentImgPath='" + documentImgPath + '\'' +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }
}
