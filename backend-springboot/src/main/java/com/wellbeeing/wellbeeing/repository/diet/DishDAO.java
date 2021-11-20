package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.Dish;
import org.jetbrains.annotations.NotNull;
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
    @Query("select d from Dish d where d.active = true and d.draft = false and (:namePart = '' or lower(d.name) like lower(concat('%', :namePart,'%')))")
    Page<Dish> findActivePublishedByNameLikeIgnoreCase(@Param("namePart") String namePart, Pageable pageable);
    @NotNull
    Optional<Dish> findById(@NotNull UUID dishId);
    @NotNull
    Page<Dish> findAll(@NotNull Pageable pageable);
    @NotNull
    List<Dish> findAll();
    List<Dish> findAllByName(String name);
    @Query( "select d from Dish d where d.id in :ids and d.active = true and d.draft = false and (:nameLike = '' or (lower(d.name) like lower(concat('%', :nameLike,'%'))))" )
    Page<Dish> findActivePublishedByDishIds(@Param("ids") List<UUID> dishIdList, @Param("nameLike") String nameLike, Pageable pageable);
    Page<Dish> findByDishCreatorIdAndActive(UUID dieticianId, boolean active, Pageable pageable);
}
