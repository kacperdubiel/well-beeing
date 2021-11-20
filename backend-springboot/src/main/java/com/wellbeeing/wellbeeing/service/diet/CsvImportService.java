package com.wellbeeing.wellbeeing.service.diet;

import org.springframework.web.multipart.MultipartFile;

public interface CsvImportService {

    boolean importProducts(MultipartFile products);
    boolean importAilments(MultipartFile ailments);
    boolean importDiets(MultipartFile diets);

}
