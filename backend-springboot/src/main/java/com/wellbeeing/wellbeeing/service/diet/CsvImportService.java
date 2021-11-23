package com.wellbeeing.wellbeeing.service.diet;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface CsvImportService {

    ArrayList<String> importProducts(MultipartFile products);
    ArrayList<String> importAilments(MultipartFile ailments);
    ArrayList<String> importDiets(MultipartFile diets);

}
