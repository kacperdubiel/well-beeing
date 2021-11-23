package com.wellbeeing.wellbeeing.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellbeeing.wellbeeing.domain.diet.Dish;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class DieticianProfile extends SpecialistProfile {
    @Column
    @OneToMany(mappedBy = "dishCreator")
    @JsonIgnore
    private List<Dish> createdDishes = new ArrayList<>();
}
