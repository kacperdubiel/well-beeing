package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.social.RoleRequest;

import javax.persistence.*;
import java.util.*;

@Entity
public class DoctorSpecialization {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "specializations")
    private List<DoctorProfile> doctors;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL)
    private List<RoleRequest> specRoleRequests = new ArrayList<>();

    public DoctorSpecialization() {

    }

    public DoctorSpecialization(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
