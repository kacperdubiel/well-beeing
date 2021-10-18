package com.wellbeeing.wellbeeing.domain.account;

import com.wellbeeing.wellbeeing.domain.diet.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@Entity
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
//    @ManyToMany
//    @JoinTable(
//            name = "profile_card_disliked_product",
//            joinColumns = @JoinColumn(name = "disliked_product_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "profile_card_id", referencedColumnName = "id")
//    )
//    private List<Product> dislikedProducts;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "preferred_diet_id", referencedColumnName = "id")
//    private Diet preferredDiet;
    private EActivityLevel activityLevel;
    @OneToOne(mappedBy = "profileCard", fetch = FetchType.LAZY, optional = false)
    private Profile profile;
    @Column
    private EGlycemicIndexLevel glycemicIndexLevel;

    public ProfileCard(double weight, int age, Profile profile) {
        this.weight = weight;
        this.age = age;
        this.profile = profile;
    }
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "diet_calculations_id", referencedColumnName = "id")
//    private ProfileDietCalculation dietCalculations;

    public UUID getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public com.wellbeeing.wellbeeing.domain.account.ESex getESex() {
        return ESex;
    }

    public List<Ailment> getAilments() {
        return ailments;
    }

//    public List<Product> getDislikedProducts() {
//        return dislikedProducts;
//    }
//
//    public Diet getPreferredDiet() {
//        return preferredDiet;
//    }

    public EActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public Profile getProfile() {
        return profile;
    }

    public EGlycemicIndexLevel getGlycemicIndexLevel() {
        return glycemicIndexLevel;
    }

//    public ProfileDietCalculation getDietCalculations() {
//        return dietCalculations;
//    }
}
