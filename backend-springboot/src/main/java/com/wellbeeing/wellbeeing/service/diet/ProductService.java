package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.ProductMacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.ProductVitaminDetail;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID productId) throws NotFoundException;
    Page<Product> getAllProducts(int numberOfElements, int page);
    Page<Product> getProductsWithNameLike(String namePart, int numberOfElements, int page);
    List<ProductMacroDetail> getProductMacroDetailsByProductId(UUID productID) throws NotFoundException;
    List<ProductVitaminDetail> getProductVitaminDetailsByProductId(UUID productID) throws NotFoundException;
    List<ProductMineralDetail> getProductMineralDetailsByProductId(UUID productID) throws NotFoundException;
    Map<String, List<?>> getAllProductDetails(UUID productID) throws NotFoundException;
    Product addProduct(Product product);
    Product updateProduct(Product product, UUID productId);
    boolean deleteProduct(UUID productId);
}
