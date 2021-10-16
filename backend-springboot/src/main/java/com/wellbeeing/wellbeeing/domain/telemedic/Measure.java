package com.wellbeeing.wellbeeing.domain.telemedic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wellbeeing.wellbeeing.domain.account.Profile;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "measures")
public class Measure {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "value")
    private double value;
    @Column(name = "measure_date")
    private Date measureDate;

    @ManyToOne
    private MeasureType measureType;
    @ManyToOne
    private Profile owner;

    public Measure() {
    }

    public Measure(@JsonProperty("id") UUID id, @JsonProperty("value") double value,
                   @JsonProperty("measureDate") Date measureDate,
                   @JsonProperty("owner") Profile owner){
        this.id = id;
        this.value = value;
        this.measureDate = measureDate;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    public MeasureType getMeasureType() {
        return measureType;
    }

    public void setMeasureType(MeasureType measureType) {
        this.measureType = measureType;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }
}

