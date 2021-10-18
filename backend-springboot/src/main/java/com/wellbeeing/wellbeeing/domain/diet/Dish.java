package com.wellbeeing.wellbeeing.domain.diet;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private String imgDishPath;
    @Column
    private String description;
    @Column
    private String recipe;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_product_details", referencedColumnName = "id")
    List<DishProductDetail> dishProductDetails;
    @Column
    private boolean isBreakfast;
    @Column
    private boolean isLunch;
    @Column
    private boolean isDinner;
    @Column
    private boolean isSnack;
    @Column
    private boolean isSupper;


}
