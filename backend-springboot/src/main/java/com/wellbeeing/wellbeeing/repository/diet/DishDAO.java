package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dishDAO")
public interface DishDAO  extends PagingAndSortingRepository<Dish, UUID> {
    @Query("select d from Dish d where lower(d.name) like lower(concat('%', :namePart,'%'))")
    Page<Dish> findByNameLikeIgnoreCase(String namePart, Pageable pageable);
    Optional<Dish> findById(UUID dishId);
    Page<Dish> findAll(Pageable pageable);
    List<Dish> findAll();
    @Query( "select d from Dish d where d.id in :ids and (:nameLike = '' or (lower(d.name) like lower(concat('%', :nameLike,'%'))))" )
    Page<Dish> findByDishIds(@Param("ids") List<UUID> dishIdList, @Param("nameLike") String nameLike, Pageable pageable);
}
