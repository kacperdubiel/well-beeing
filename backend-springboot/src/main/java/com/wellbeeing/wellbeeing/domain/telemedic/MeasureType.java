package com.wellbeeing.wellbeeing.domain.telemedic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "measureTypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasureType {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "unit")
    private String unit;

    @JsonIgnore
    @OneToMany(mappedBy = "measureType")
    private List<Measure> measures;

}

