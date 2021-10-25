package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.MacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.MineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.VitaminDetail;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID productId) throws NotFoundException;
    Page<Product> getAllProducts(int numberOfElements, int page);
    Page<Product> getProductsWithNameLike(String namePart, int numberOfElements, int page);
    List<MacroDetail> getProductMacroDetailsByProductId(UUID productID) throws NotFoundException;
    List<VitaminDetail> getProductVitaminDetailsByProductId(UUID productID) throws NotFoundException;
    List<MineralDetail> getProductMineralDetailsByProductId(UUID productID) throws NotFoundException;
    Map<String, List<?>> getAllProductDetails(UUID productID) throws NotFoundException;

}
