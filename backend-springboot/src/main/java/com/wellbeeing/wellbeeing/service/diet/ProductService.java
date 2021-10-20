package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.MacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.MineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.VitaminDetail;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID productId) throws NotFoundException;
    List<Product> getAllProducts(int numberOfElements, int page);
    List<Product> getProductsWithNameLike(String namePart, int numberOfElements, int page);
    List<MacroDetail> getProductMacroDetailsByProductId(UUID productID) throws NotFoundException;
    List<VitaminDetail> getProductVitaminDetailsByProductId(UUID productID) throws NotFoundException;
    List<MineralDetail> getProductMineralDetailsByProductId(UUID productID) throws NotFoundException;
}
