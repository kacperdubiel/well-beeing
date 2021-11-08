package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.ProductMacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.ProductMineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.ProductVitaminDetail;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
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
    public Product getProductById(UUID productId) throws NotFoundException {
        Product product = productDAO.findById(productId).orElse(null);
        if(product != null)
            return product;
        throw new NotFoundException("Product with id: " + productId + "not found");
    }

    @Override
    public Page<Product> getAllProducts(int numberOfElements, int page) {
        return productDAO.findAll(PageRequest.of(page, numberOfElements, Sort.by("name")));
    }

    @Override
    public Page<Product> getProductsWithNameLike(String namePart, int numberOfElements, int page) {
        return productDAO.findByNameLikeIgnoreCase(namePart, PageRequest.of(page,
                 numberOfElements, Sort.by("name")));
    }

    @Override
    public List<ProductMacroDetail> getProductMacroDetailsByProductId(UUID productId) throws NotFoundException {
        Product product = getProductById(productId);
        return product.getMacroDetails();

    }

    @Override
    public List<ProductVitaminDetail> getProductVitaminDetailsByProductId(UUID productId) throws NotFoundException {
        Product product = getProductById(productId);
        return product.getVitaminDetails();
    }


    @Override
    public List<ProductMineralDetail> getProductMineralDetailsByProductId(UUID productId) throws NotFoundException {
        Product product = getProductById(productId);
        return product.getMineralDetails();
    }

    @Override
    public Map<String, List<?>> getAllProductDetails(UUID productID) throws NotFoundException {
        Map<String, List<?>> result = new HashMap<>();
        result.put("macroDetails", getProductMacroDetailsByProductId(productID));
        result.put("minerals", getProductMineralDetailsByProductId(productID));
        result.put("vitamins", getProductVitaminDetailsByProductId(productID));
        return result;
    }

    @Override
    public Product addProduct(Product product) {
        productDAO.save(product);
        product.getMineralDetails().forEach(md -> md.setElementsProduct(product));
        product.getMacroDetails().forEach(md -> md.setElementsProduct(product));
        product.getVitaminDetails().forEach(vd -> vd.setElementsProduct(product));
        return productDAO.save(product);
    }

    @Override
    public Product updateProduct(Product product, UUID productId) {
        product.setId(productId);
        addProduct(product);
        return product;
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        productDAO.deleteById(productId);
        return true;
    }
}
