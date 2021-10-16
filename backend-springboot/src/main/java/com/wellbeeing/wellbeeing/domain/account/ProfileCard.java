package com.wellbeeing.wellbeeing.domain.account;

import com.wellbeeing.wellbeeing.domain.diet.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class ProfileCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private double height;
    @Column
    private double weight;
    @Column
    private int age;
    @Column
    private ESex ESex;
    @ManyToMany
    @JoinTable(
            name = "profile_card_ailment",
            joinColumns = @JoinColumn(name = "ailment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    )
    private List<Ailment> ailments;
    @ManyToMany
    @JoinTable(
            name = "profile_card_disliked_product",
            joinColumns = @JoinColumn(name = "disliked_product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
    )
    private List<Product> dislikedProducts;
    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_diet_id", referencedColumnName = "id")
    private Diet preferredDiet;*/
    @Column
    private boolean isVegan;
    @Column
    private boolean isVegetarian;
    @Column
    private EActivityLevel activityLevel;
    @Column
    private int trainingActivityTimePerWeek;
    @Column
    private EDietGoal dietGoal;
    @OneToOne(mappedBy = "profileCard", fetch = FetchType.LAZY, optional = false)
    private Profile profile;
   /* @Column
    private EGlycemicIndexLevel glycemicIndexLevel;*/
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "diet_calculations_id", referencedColumnName = "id")
    private ProfileDietCalculation dietCalculations;



