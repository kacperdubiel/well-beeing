package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.SportLabel;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportLabelAilment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sport_label_id")
    private SportLabel sportLabel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ailment_id")
    @JsonIgnore
    private Ailment ailment;
    @Column
    private boolean isPositive = true;
    //range(0.0-1.0)
    @Column
    private double impactWeight = 0.5;
}
