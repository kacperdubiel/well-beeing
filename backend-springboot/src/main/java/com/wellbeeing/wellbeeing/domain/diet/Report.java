package com.wellbeeing.wellbeeing.domain.diet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "owner_profile_id")
    @JsonIgnore
    private Profile reportOwner;
    @Column
    private Date reportDate;
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    List<ReportProductDetail> productDetailsList;
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    List<ReportDishDetail> dishDetailsList;
    // DERIVED FORM PRODUCTS AND DISHES
    @Column
    double derivedCalories;
    @Column
    double derivedCarbohydrates;
    @Column
    double derivedProteins;
    @Column
    double derivedFats;
}
