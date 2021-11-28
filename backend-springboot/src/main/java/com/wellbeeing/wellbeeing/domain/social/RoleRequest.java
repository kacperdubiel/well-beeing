package com.wellbeeing.wellbeeing.domain.social;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.DoctorSpecialization;
import com.wellbeeing.wellbeeing.domain.account.ERole;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id")
    private DoctorSpecialization specialization = null;

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
