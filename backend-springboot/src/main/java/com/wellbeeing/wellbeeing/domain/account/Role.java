package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@AllArgsConstructor
@Table(name = "wb_role")
public class Role implements GrantedAuthority {

    public static final String BASIC_USER = "BASIC_USER";
    public static final String DOCTOR_USER = "DOCTOR_USER";
    public static final String TRAINER_USER = "TRAINER_USER";
    public static final String DIETICIAN_USER = "DIETICIAN_USER";

    @Id
    @GeneratedValue
    private UUID role_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", unique = true, nullable = false)
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {

    }

    public Role(@JsonProperty("name") ERole role_name){
//        this.role_id = UUID.randomUUID();
        this.name = role_name;
    }
    public ERole getRole() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", name=" + name +
                '}';
    }
}
