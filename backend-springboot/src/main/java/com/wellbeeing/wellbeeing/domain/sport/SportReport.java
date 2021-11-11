package com.wellbeeing.wellbeeing.domain.sport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.account.Profile;
import com.wellbeeing.wellbeeing.domain.diet.ReportDishDetail;
import com.wellbeeing.wellbeeing.domain.diet.ReportProductDetail;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "owner_profile_id")
    @JsonIgnore
    private Profile reportOwner;
    @Column
    private LocalDate reportDate;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "sportReport", cascade = CascadeType.ALL)
    List<ReportExercise> exerciseDetailsList = new ArrayList<>();

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
//    List<ReportDishDetail> dishDetailsList = new ArrayList<>();
}
