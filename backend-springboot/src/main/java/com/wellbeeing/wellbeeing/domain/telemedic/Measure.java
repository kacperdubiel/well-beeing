package com.wellbeeing.wellbeeing.domain.telemedic;

import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "measures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Measure {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "value", nullable = false)
    private double value;
    @Column(name = "measure_date", nullable = false)
    private LocalDateTime measureDate;

    @ManyToOne
    private MeasureType measureType;
    @ManyToOne
    private Profile owner;

}

