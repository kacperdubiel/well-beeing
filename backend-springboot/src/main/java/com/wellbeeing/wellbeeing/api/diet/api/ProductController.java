package com.wellbeeing.wellbeeing.api.diet.api;

import com.wellbeeing.wellbeeing.domain.diet.product.Product;
import com.wellbeeing.wellbeeing.domain.diet.product.ProductMacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.product.ProductMineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.product.ProductVitaminDetail;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.PaginatedResponse;
import com.wellbeeing.wellbeeing.service.diet.ProductService;
import com.wellbeeing.wellbeeing.util.CsvProductImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    @Qualifier("csvProductImporter")
    private CsvProductImporter csvProductImporter;

    @Autowired
    public ProductController(@Qualifier("productService") ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(path = "/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable("productId") UUID productID) throws NotFoundException {
        Product actProduct = productService.getProductById(productID);
        return new ResponseEntity<>(actProduct, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/import", method = RequestMethod.POST)
    public ResponseEntity<?> importProducts() throws Exception {
        csvProductImporter.readProductsFromCsv("C:\\Users\\User\\Desktop\\well-beeing\\well-beeing\\backend-springboot\\src\\main\\java\\com\\wellbeeing\\wellbeeing\\MyFoodData-Nutrition-Facts-SpreadSheet-Detailed-Release-1-0.csv");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(path = "/product", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductsPaginated(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size){
        Page<Product> productsPage = productService.getAllProducts(size, page);
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
                                                     @RequestParam(value = "nameLike", defaultValue = "") String nameLike){
        if(nameLike.equals("")){
            return getAllProductsPaginated(page, size);
        }
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
        List<ProductMacroDetail> actMacros = productService.getProductMacroDetailsByProductId(productID);
        return new ResponseEntity<>(actMacros, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/minerals", method = RequestMethod.GET)
    public ResponseEntity<?> getProductMineralDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        List<ProductMineralDetail> actMacros = productService.getProductMineralDetailsByProductId(productID);
        return new ResponseEntity<>(actMacros, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/vitamins", method = RequestMethod.GET)
    public ResponseEntity<?> getProductVitaminDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        List<ProductVitaminDetail> actMacros = productService.getProductVitaminDetailsByProductId(productID);
        return new ResponseEntity<>(actMacros, HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}/details", method = RequestMethod.GET)
    public ResponseEntity<?> getProductAllDetailsByProductId(@PathVariable("productId") UUID productID) throws NotFoundException {
        Map<String, List<?>> result = productService.getAllProductDetails(productID);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody @NonNull Product product)  {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> addProduct(@RequestBody @NonNull Product product, @PathVariable("productId") UUID productId)  {
        return new ResponseEntity<>(productService.updateProduct(product, productId), HttpStatus.OK);
    }

    @RequestMapping(path = "/product/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> addProduct(@PathVariable("productId") UUID productId)  {
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }
}
