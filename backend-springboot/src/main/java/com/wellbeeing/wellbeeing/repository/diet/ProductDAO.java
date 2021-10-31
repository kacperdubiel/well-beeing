package com.wellbeeing.wellbeeing.repository.diet;

import com.wellbeeing.wellbeeing.domain.diet.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository("productDAO")
public interface ProductDAO extends PagingAndSortingRepository<Product, UUID> {
    Page<Product> findByNameStartingWithIgnoreCase(String name, Pageable pageable);
    Optional<Product> findById(UUID productId);
    Page<Product> findAll(Pageable pageable);
}
