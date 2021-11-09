package com.wellbeeing.wellbeeing.util;

import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.NutritionLabel;
import com.wellbeeing.wellbeeing.domain.diet.Product;
import com.wellbeeing.wellbeeing.domain.diet.type.EAilmentType;
import com.wellbeeing.wellbeeing.repository.diet.AilmentDAO;
import com.wellbeeing.wellbeeing.repository.diet.NutritionLabelDAO;
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
    private NutritionLabelDAO nutritionLabelDAO;
    private AilmentDAO ailmentDAO;


    @Autowired
    public CsvProductImporter(@Qualifier("productService") ProductService productService,
                              @Qualifier("nutritionLabelDAO") NutritionLabelDAO nutritionLabelDAO,
                              @Qualifier("ailmentDAO") AilmentDAO ailmentDAO){
        this.productService = productService;
        this.nutritionLabelDAO = nutritionLabelDAO;
        this.ailmentDAO = ailmentDAO;
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
            importAilments();
            importNutritionLabels();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return numberOfProductsImported;
    }
    private void importNutritionLabels(){
        NutritionLabel nl1 = NutritionLabel.builder()
                .name("KETO")
                .description("KETO")
                .build();
        nutritionLabelDAO.save(nl1);
        NutritionLabel nl2 = NutritionLabel.builder()
                .name("WEGAŃSKIE")
                .description("WEGAŃSKIE")
                .build();
        nutritionLabelDAO.save(nl2);
        NutritionLabel nl3 = NutritionLabel.builder()
                .name("BEZGLUTENOWE")
                .description("BEZGLUTENOWE")
                .build();
        nutritionLabelDAO.save(nl3);
        NutritionLabel nl4 = NutritionLabel.builder()
                .name("WEGETARIAŃSKIE")
                .description("WEGETARIAŃSKIE")
                .build();
        nutritionLabelDAO.save(nl4);
    }
    private void importAilments(){
        Ailment a1 = Ailment.builder()
                .name("Cukrzyca")
                .description("Choroba")
                .isLowGlycemicIndexRecommended(true)
                .changeInProteinsGramPerKilogram(0)
                .changeInCalories(0)
                .type(EAilmentType.ILLNESS)
                .build();
        ailmentDAO.save(a1);
        Ailment a2 = Ailment.builder()
                .name("Choroba 1")
                .description("Choroba")
                .isLowGlycemicIndexRecommended(false)
                .changeInProteinsGramPerKilogram(0)
                .changeInCalories(0)
                .type(EAilmentType.ILLNESS)
                .build();
        ailmentDAO.save(a2);
        Ailment a3 = Ailment.builder()
                .name("Choroba 2")
                .description("Choroba")
                .isLowGlycemicIndexRecommended(false)
                .changeInProteinsGramPerKilogram(0)
                .changeInCalories(0)
                .type(EAilmentType.ILLNESS)
                .build();
        ailmentDAO.save(a3);
    }
}
