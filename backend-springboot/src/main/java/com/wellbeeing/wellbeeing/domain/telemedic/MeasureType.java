package com.wellbeeing.wellbeeing.domain.telemedic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "measureTypes")
public class MeasureType {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;
    @Column(name = "unit")
    private String unit;

    @JsonIgnore
    @OneToMany(mappedBy = "measureType")
    private List<Measure> measures;

    public MeasureType() {

    }

    public MeasureType(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                       @JsonProperty("unit") String unit){
        this.id = id;
        this.name = name;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}

