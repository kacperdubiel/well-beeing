package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("productDAO")
public interface ProductDAO extends PagingAndSortingRepository<Product, UUID> {
    @Query("select p from Product p where :namePart='' or lower(p.name) like lower(concat('%', :namePart,'%'))")
    Page<Product> findByNameLikeIgnoreCase(@Param("namePart") String namePart, Pageable pageable);
    Optional<Product> findById(UUID productId);
    Page<Product> findAll(Pageable pageable);
}
