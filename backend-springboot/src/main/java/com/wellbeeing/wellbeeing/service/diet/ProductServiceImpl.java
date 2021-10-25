package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.MacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.MineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.VitaminDetail;
import com.wellbeeing.wellbeeing.repository.diet.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(@Qualifier("productDAO") ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public Product getProductById(UUID productId) {
        return productDAO.findById(productId).orElse(null);
    }

    @Override
    public Page<Product> getAllProducts(int numberOfElements, int page) {
        return productDAO.findAll(PageRequest.of(page, numberOfElements, Sort.by("name")));
    }

    @Override
    public Page<Product> getProductsWithNameLike(String namePart, int numberOfElements, int page) {
        return productDAO.findByNameStartingWith(namePart, PageRequest.of(page,
                 numberOfElements, Sort.by("name")));
    }

    @Override
    public List<MacroDetail> getProductMacroDetailsByProductId(UUID productId) {
        Product product = getProductById(productId);
        if(product != null)
            return getProductById(productId).getMacroDetails();
        else
            return null;

    }

    @Override
    public List<VitaminDetail> getProductVitaminDetailsByProductId(UUID productId) {
        Product product = getProductById(productId);
        if(product != null)
            return getProductById(productId).getVitaminDetails();
        else
            return null;
    }


    @Override
    public List<MineralDetail> getProductMineralDetailsByProductId(UUID productId) {
        Product product = getProductById(productId);
        if(product != null)
            return getProductById(productId).getMineralDetails();
        else
            return null;
    }

    @Override
    public Map<String, List<?>> getAllProductDetails(UUID productID) {
        Product product = getProductById(productID);
        if(product != null){
            Map<String, List<?>> result = new HashMap<>();
            result.put("macroDetails", getProductMacroDetailsByProductId(productID));
            result.put("minerals", getProductMineralDetailsByProductId(productID));
            result.put("vitamins", getProductVitaminDetailsByProductId(productID));
            return result;
        }
        return null;
    }
}
