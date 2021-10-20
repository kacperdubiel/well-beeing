package com.wellbeeing.wellbeeing.api.diet;

import com.wellbeeing.wellbeeing.service.diet.ProductService;
import com.wellbeeing.wellbeeing.service.diet.ProductServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProductController {
    private ProductService productService;


}
