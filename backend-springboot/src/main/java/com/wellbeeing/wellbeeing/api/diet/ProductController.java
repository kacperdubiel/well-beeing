package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.domain.diet.MacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.MineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.VitaminDetail;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.diet.ProductService;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("productService") ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(path = "/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable("productId") UUID productID) throws NotFoundException {
        Product actProduct = productService.getProductById(productID);
        return new ResponseEntity<>(actProduct, HttpStatus.OK);
    }

    @RequestMapping(path = "/product", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductsPaginated(@RequestParam(value = "page", defaultValue = "0") String page,
                                                     @RequestParam(value = "size", defaultValue = "10") String size){
        Page<Product> productsPage = productService.getAllProducts(Integer.parseInt(size), Integer.parseInt(page));
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(productsPage.getNumber())
                .totalItems(productsPage.getTotalElements())
                .totalPages(productsPage.getTotalPages())
                .objects(productsPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(path = "/product/search", method = RequestMethod.GET)
    public ResponseEntity<?> getProductsWithNameLike(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                     @RequestParam(value = "nameLike", defaultValue = "a") String nameLike){
        Page<Product> productsPage = productService.getProductsWithNameLike(nameLike, size, page);
        PaginatedResponse response = PaginatedResponse.builder()
                .currentPage(productsPage.getNumber())
                .totalItems(productsPage.getTotalElements())
                .totalPages(productsPage.getTotalPages())
                .objects(productsPage.getContent())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/detailed-macros", method = RequestMethod.GET)
    public ResponseEntity<?> getProductMacroDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        List<MacroDetail> actMacros = productService.getProductMacroDetailsByProductId(productID);
        return new ResponseEntity<>(actMacros, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/minerals", method = RequestMethod.GET)
    public ResponseEntity<?> getProductMineralDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        List<MineralDetail> actMacros = productService.getProductMineralDetailsByProductId(productID);
        return new ResponseEntity<>(actMacros, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/vitamins", method = RequestMethod.GET)
    public ResponseEntity<?> getProductVitaminDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        List<VitaminDetail> actMacros = productService.getProductVitaminDetailsByProductId(productID);
        return new ResponseEntity<>(actMacros, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/details", method = RequestMethod.GET)
    public ResponseEntity<?> getProductAllDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        Map<String, List<?>> result = productService.getAllProductDetails(productID);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
