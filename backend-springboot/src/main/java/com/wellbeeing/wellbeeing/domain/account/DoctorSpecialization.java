package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class DoctorSpecialization {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "specializations")
    private List<DoctorProfile> doctors;

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
