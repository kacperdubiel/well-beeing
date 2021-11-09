package com.wellbeeing.wellbeeing.util;

import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.service.diet.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Component("csvProductImporter")
public class CsvProductImporter {

    private ProductService productService;

    @Autowired
    public CsvProductImporter(@Qualifier("productService") ProductService productService){
        this.productService = productService;
    }

    @Override
    public String toString() {
        return "heh";
    }

    public int readProductsFromCsv(String fileName){
        int numberOfProductsImported = 0;
        Path pathToCsv = Paths.get(fileName);

        try(BufferedReader br = Files.newBufferedReader(pathToCsv, StandardCharsets.US_ASCII)){
            String line = br.readLine();
            line = br.readLine();
            while(numberOfProductsImported<30){
                String[] attributes = line.split(";");

                String name = attributes[1];
                double calories = Double.parseDouble(attributes[3]);
                double fats = Double.parseDouble(attributes[4].replace(',', '.'));
                double proteins = Double.parseDouble(attributes[5].replace(',', '.'));
                double carbs = Double.parseDouble(attributes[6].replace(',', '.'));

                Product product = Product.builder()
                        .caloriesPerHundredGrams(calories)
                        .carbohydratesPerHundredGrams(carbs)
                        .fatsPerHundredGrams(fats)
                        .proteinsPerHundredGrams(proteins)
                        .macroDetails(new ArrayList<>())
                        .vitaminDetails(new ArrayList<>())
                        .mineralDetails(new ArrayList<>())
                        .name(name)
                        .build();

                productService.addProduct(product);

                numberOfProductsImported++;
                line = br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return numberOfProductsImported;
    }
}
