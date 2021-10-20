package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dishDAO")
public interface DishDAO  extends PagingAndSortingRepository<Dish, UUID> {
    Page<Dish> findByNameStartingWith(String name, Pageable pageable);
    Optional<Dish> findById(UUID dishId);
    Page<Dish> findAll(Pageable pageable);
}
