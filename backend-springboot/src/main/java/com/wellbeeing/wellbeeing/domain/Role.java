package com.wellbeeing.wellbeeing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "wb_role")
public class Role{

    public static final String BASIC_USER = "BASIC_USER";
    public static final String DOCTOR_USER = "DOCTOR_USER";
    public static final String TRAINER_USER = "TRAINER_USER";
    public static final String DIETICIAN_USER = "DIETICIAN_USER";

    @Id
    private UUID role_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", unique = true, nullable = false)
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {

    }

    public Role(@JsonProperty("name") ERole role_name){
        this.role_id = UUID.randomUUID();
        this.name = role_name;
    }
    public ERole getRole() {
        return name;
    }
}
