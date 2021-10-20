package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.MacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.MineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.VitaminDetail;
import com.wellbeeing.wellbeeing.repository.diet.ProductDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
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
        throw new NotFoundException("Product not found");
    }

    @Override
    public List<Product> getAllProducts(int numberOfElements, int page) {
        return productDAO.findAll(PageRequest.of(page, numberOfElements, Sort.by("name"))).toList();
    }

    @Override
    public List<Product> getProductsWithNameLike(String namePart, int numberOfElements, int page) {
        return productDAO.findByNameStartingWith(namePart, PageRequest.of(page,
                numberOfElements + numberOfElements, Sort.by("name"))).toList();
    }

    @Override
    public List<MacroDetail> getProductMacroDetailsByProductId(UUID productId) throws NotFoundException {
        return getProductById(productId).getMacroDetails();

    }

    @Override
    public List<VitaminDetail> getProductVitaminDetailsByProductId(UUID productId) throws NotFoundException {
        return getProductById(productId).getVitaminDetails();
    }


    @Override
    public List<MineralDetail> getProductMineralDetailsByProductId(UUID productId) throws NotFoundException {
        return getProductById(productId).getMineralDetails();
    }
}
