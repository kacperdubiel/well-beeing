package com.wellbeeing.wellbeeing.service.diet;

import com.wellbeeing.wellbeeing.domain.diet.*;
import com.wellbeeing.wellbeeing.domain.diet.product.Product;
import com.wellbeeing.wellbeeing.domain.diet.product.ProductMacroDetail;
import com.wellbeeing.wellbeeing.domain.diet.product.ProductMineralDetail;
import com.wellbeeing.wellbeeing.domain.diet.product.ProductVitaminDetail;
import com.wellbeeing.wellbeeing.domain.diet.type.*;
import com.wellbeeing.wellbeeing.domain.exception.InvalidImportDataException;
import com.wellbeeing.wellbeeing.repository.diet.AilmentDAO;
import com.wellbeeing.wellbeeing.repository.diet.DietDAO;
import com.wellbeeing.wellbeeing.repository.diet.ProductDAO;
import com.wellbeeing.wellbeeing.service.files.FileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service("csvImportService")
public class CsvImportServiceImpl implements CsvImportService {
    @Value("/uploads")
    private String uploadPath;

    private final ProductDAO productDAO;
    private final AilmentDAO ailmentDAO;
    private final DietDAO dietDAO;

    private final FileService fileService;


    public CsvImportServiceImpl(@Qualifier("productDAO") ProductDAO productDAO,
                                @Qualifier("ailmentDAO") AilmentDAO ailmentDAO,
                                @Qualifier("dietDAO") DietDAO dietDAO,
                                @Qualifier("fileService")FileService fileService){
        this.productDAO = productDAO;
        this.ailmentDAO = ailmentDAO;
        this.dietDAO = dietDAO;
        this.fileService = fileService;
    }

    @Override
    public ArrayList<String> importProducts(MultipartFile productsToImport) {
        String productsPath = fileService.save(productsToImport);
        Path file = Paths.get(uploadPath)
                .resolve(productsPath);
        ArrayList<String> errorMessages = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)){
            String[] header = (br.readLine()).split(";");
            String line;
            int lineNumber = 1;
            while((line = br.readLine()) != null){
                try {
                    String[] attributes = line.split(";");

                    String name = checkAndReturnPosition(0, attributes, lineNumber, header, "STRING");
                    String glycemicIndex = checkAndReturnPosition(1, attributes, lineNumber, header, "STRING");
                    if (mapGlycemicIndex(glycemicIndex).equals(EGlycemicIndexLevel.ANY))
                        throw new InvalidImportDataException("invalid glycemic index enum value: " + header[1] + "line" + lineNumber);
                    String calories = checkAndReturnPosition(2, attributes, lineNumber, header, "DOUBLE");
                    String fats = checkAndReturnPosition(3, attributes, lineNumber, header, "DOUBLE");
                    String saturatedFats = checkAndReturnPosition(4, attributes, lineNumber, header, "DOUBLE");
                    String carbohydrates = checkAndReturnPosition(5, attributes, lineNumber, header, "DOUBLE");
                    String sugar = checkAndReturnPosition(6, attributes, lineNumber, header, "DOUBLE");
                    String proteins = checkAndReturnPosition(7, attributes, lineNumber, header, "DOUBLE");
                    String fiber = checkAndReturnPosition(8, attributes, lineNumber, header, "DOUBLE");
                    String cholesterol = checkAndReturnPosition(9, attributes, lineNumber, header, "DOUBLE");
                    String salt = checkAndReturnPosition(10, attributes, lineNumber, header, "DOUBLE");
                    String caffeine = checkAndReturnPosition(11, attributes, lineNumber, header, "DOUBLE");

                    String vegeProteins = checkAndReturnPosition(12, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String animalProteins = checkAndReturnPosition(13, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String polyunsaturatedFats = checkAndReturnPosition(14, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String monounsaturatedFats = checkAndReturnPosition(15, attributes, lineNumber, header, "DOUBLE_NULLABLE");

                    String zinc = checkAndReturnPosition(16, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String phosphorus = checkAndReturnPosition(17, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String iodine = checkAndReturnPosition(18, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String magnesium = checkAndReturnPosition(19, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String copper = checkAndReturnPosition(20, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String potassium = checkAndReturnPosition(21, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String selenium = checkAndReturnPosition(22, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String sodium = checkAndReturnPosition(23, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String calcium = checkAndReturnPosition(24, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String iron = checkAndReturnPosition(25, attributes, lineNumber, header, "DOUBLE_NULLABLE");

                    String a = checkAndReturnPosition(26, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String b1 = checkAndReturnPosition(27, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String b2 = checkAndReturnPosition(28, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String b5 = checkAndReturnPosition(29, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String b6 = checkAndReturnPosition(30, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String b12 = checkAndReturnPosition(31, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String c = checkAndReturnPosition(32, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String d = checkAndReturnPosition(33, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String e = checkAndReturnPosition(34, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String pp = checkAndReturnPosition(35, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String k = checkAndReturnPosition(36, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String folic = checkAndReturnPosition(37, attributes, lineNumber, header, "DOUBLE_NULLABLE");
                    String biotin = checkAndReturnPosition(38, attributes, lineNumber, header, "DOUBLE_NULLABLE");

                    Product newProd = Product.builder()
                            .name(name)
                            .glycemicIndexLevel(mapGlycemicIndex(glycemicIndex))
                            .caloriesPerHundredGrams(Double.parseDouble(calories))
                            .fatsPerHundredGrams(Double.parseDouble(fats))
                            .saturatedFatsPerHundredGrams(Double.parseDouble(saturatedFats))
                            .carbohydratesPerHundredGrams(Double.parseDouble(carbohydrates))
                            .sugarsPerHundredGrams(Double.parseDouble(sugar))
                            .proteinsPerHundredGrams(Double.parseDouble(proteins))
                            .fiberPerHundredGrams(Double.parseDouble(fiber))
                            .cholesterolPerHundredGrams(Double.parseDouble(cholesterol))
                            .saltPerHundredGrams(Double.parseDouble(salt))
                            .caffeinePerHundredGrams(Double.parseDouble(caffeine))
                            .build();
                    Product savedProd = productDAO.save(newProd);

                    savedProd.setMacroDetails(new ArrayList<>());
                    savedProd.setVitaminDetails(new ArrayList<>());
                    savedProd.setMineralDetails(new ArrayList<>());

                    addVitaminToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EVitamin.A, a);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.B1, b1);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.B2, b2);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.B5, b5);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.B6, b6);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.B12, b12);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.C, c);
                    addVitaminToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EVitamin.D, d);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.E, e);
                    addVitaminToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EVitamin.PP, pp);
                    addVitaminToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EVitamin.K, k);
                    addVitaminToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EVitamin.FOLIC_ACID, folic);
                    addVitaminToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EVitamin.BIOTIN, biotin);

                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.ZINC, zinc);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.PHOSPHORUS, phosphorus);
                    addMineralToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EMineral.IODINE, iodine);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.MAGNESIUM, magnesium);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.COPPER, copper);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.POTASSIUM, potassium);
                    addMineralToProduct(savedProd, EWeightMeasure.MICRO_GRAM, EMineral.SELENIUM, selenium);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.SODIUM, sodium);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.CALCIUM, calcium);
                    addMineralToProduct(savedProd, EWeightMeasure.MILLI_GRAM, EMineral.IRON, iron);

                    addDetailedMacroToProduct(savedProd, EDetailedMacro.ANIMAL_PROTEINS, animalProteins);
                    addDetailedMacroToProduct(savedProd, EDetailedMacro.VEGETABLE_PROTEINS, vegeProteins);
                    addDetailedMacroToProduct(savedProd, EDetailedMacro.MONOUNSATURATED_FATS, monounsaturatedFats);
                    addDetailedMacroToProduct(savedProd, EDetailedMacro.POLYUNSATURATED_FATS, polyunsaturatedFats);

                    productDAO.save(savedProd);
                    lineNumber++;
                }
                catch(InvalidImportDataException e){
                    errorMessages.add(e.getMessage());
                    lineNumber++;

                }
                catch(Exception e){
                    errorMessages.add("Nieznany błąd w linii:  " + lineNumber);
                    lineNumber++;
                }
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            errorMessages.add(ioe.getMessage());
        }
        return errorMessages;
    }

    @Override
    public ArrayList<String> importAilments(MultipartFile ailmentsToImport) {
        String ailmentsPath = fileService.save(ailmentsToImport);
        Path file = Paths.get(uploadPath)
                .resolve(ailmentsPath);
        ArrayList<String> errorMessages = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)){
            String[] header = (br.readLine()).split(";");
            String line;
            int lineNumber = 1;
            while((line = br.readLine()) != null){
                try {
                    String[] attributes = line.split(";");
                    String name = checkAndReturnPosition(0, attributes, lineNumber, header, "STRING");
                    String description = checkAndReturnPosition(1, attributes, lineNumber, header, "STRING");
                    String type = checkAndReturnPosition(2, attributes, lineNumber, header, "STRING");
                    if (mapAilmentType(type) == null) {
                        throw new InvalidImportDataException("Nieprawidłowy typ wyliczeniowy: " + header[2] + " w linii " + lineNumber);
                    }
                    String caloriesChange = checkAndReturnPosition(3, attributes, lineNumber, header, "DOUBLE");
                    String proteinChange = checkAndReturnPosition(4, attributes, lineNumber, header, "DOUBLE");
                    String glycemic = checkAndReturnPosition(5, attributes, lineNumber, header, "STRING");

                    Ailment newAilment = Ailment.builder()
                            .name(name)
                            .description(description)
                            .type(mapAilmentType(type))
                            .isLowGlycemicIndexRecommended(mapBoolean(glycemic))
                            .changeInCalories(Double.parseDouble(caloriesChange))
                            .changeInProteinsGramPerKilogram(Double.parseDouble(proteinChange))
                            .build();
                    ailmentDAO.save(newAilment);

                    lineNumber++;
                }
                catch (InvalidImportDataException e){
                    errorMessages.add(e.getMessage());
                    lineNumber++;
                }
                catch (Exception e){
                    errorMessages.add("Nieznany błąd w linii " + lineNumber);
                    lineNumber++;
                }
            }
        }
        catch (IOException ioe) {
            errorMessages.add(ioe.getMessage());
        }
        return errorMessages;
    }

    @Override
    public ArrayList<String> importDiets(MultipartFile dietsToImport) {
        String dietsPath = fileService.save(dietsToImport);
        Path file = Paths.get(uploadPath)
                .resolve(dietsPath);
        ArrayList<String> errorMessages = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String[] header = (br.readLine()).split(";");
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                try {
                    String[] attributes = line.split(";");
                    String name = checkAndReturnPosition(0, attributes, lineNumber, header, "STRING");
                    String description = checkAndReturnPosition(1, attributes, lineNumber, header, "STRING");
                    String connectedWithMacros = checkAndReturnPosition(2, attributes, lineNumber, header, "STRING");

                    Diet newDiet = Diet.builder()
                            .name(name)
                            .description(description)
                            .connectedWithMacros(mapBoolean(connectedWithMacros))
                            .build();

                    dietDAO.save(newDiet);
                    lineNumber++;
                } catch (InvalidImportDataException e) {
                    errorMessages.add(e.getMessage());
                    lineNumber++;
                } catch (Exception e) {
                    errorMessages.add("Nieznany błąd w linii " + lineNumber);
                    lineNumber++;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            errorMessages.add(ioe.getMessage());
        }
        return errorMessages;
    }

    private boolean checkIfNotEmpty(String input){
        return !input.equals("") && !input.equals("NULL");
    }

    private String checkAndReturnPosition(int position, String[] attributes, int lineNumber, String[] header, String checkType) throws InvalidImportDataException {
        switch (checkType) {
            case "DOUBLE":
                if (checkIfNotEmpty(attributes[position]) && checkIfNumber(attributes[position]))
                    return attributes[position];
                else throw new InvalidImportDataException("Nieprawidłowa wartość: " + header[position] + " w linii " + lineNumber);
            case "STRING":
                if (checkIfNotEmpty(attributes[position])) return attributes[position];
                else throw new InvalidImportDataException("Nieprawidłowa wartość: " + header[position] + " w linii " + lineNumber);
            case "DOUBLE_NULLABLE":
                if (checkIfNumber(attributes[position]))
                    return attributes[position];
                else return "";
            default:
                return "";
        }
    }

    private boolean checkIfNumber(String string){
        try {
            double d = Double.parseDouble(string);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    private EGlycemicIndexLevel mapGlycemicIndex(String index){
        if (index == null)
            return EGlycemicIndexLevel.ANY;
        switch (index.toUpperCase()) {
            case "VERY_LOW":
                return EGlycemicIndexLevel.VERY_LOW;
            case "LOW":
                return EGlycemicIndexLevel.LOW;
            case "MEDIUM":
                return EGlycemicIndexLevel.MEDIUM;
            case "HIGH":
                return EGlycemicIndexLevel.HIGH;
            case "VERY_HIGH":
                return EGlycemicIndexLevel.VERY_HIGH;
            default:
                return EGlycemicIndexLevel.ANY;
        }
    }

    private EAilmentType mapAilmentType(String type){
        switch (type.toUpperCase()) {
            case "ILLNESS":
                return EAilmentType.ILLNESS;
            case "ALLERGY":
                return EAilmentType.ALLERGY;
            case "INJURY":
                return EAilmentType.INJURY;
            case "PHYSICAL_CONDITION":
                return EAilmentType.PHYSICAL_CONDITION;
            default:
                return null;
        }
    }

    private boolean mapBoolean(String bool){
        return "TRUE".equals(bool.toUpperCase());
    }

    private void addVitaminToProduct(Product product, EWeightMeasure weightMeasure, EVitamin vitamin, String value){
        if(!value.equals("")){
            product.getVitaminDetails().add(ProductVitaminDetail.builder()
                    .vitaminType(vitamin)
                    .elementsProduct(product)
                    .amountOfElementPerHundredGrams(Double.parseDouble(value))
                    .measureType(weightMeasure)
                    .build());
        }
    }

    private void addMineralToProduct(Product product, EWeightMeasure weightMeasure, EMineral mineral, String value){
        if(!value.equals("")){
            product.getMineralDetails().add(ProductMineralDetail.builder()
                    .mineralType(mineral)
                    .elementsProduct(product)
                    .amountOfElementPerHundredGrams(Double.parseDouble(value))
                    .measureType(weightMeasure)
                    .build());
        }
    }

    private void addDetailedMacroToProduct(Product product, EDetailedMacro macro, String value){
        if(!value.equals("")){
            product.getMacroDetails().add(ProductMacroDetail.builder()
                    .macroType(macro)
                    .elementsProduct(product)
                    .amountOfElementPerHundredGrams(Double.parseDouble(value))
                    .measureType(EWeightMeasure.GRAM)
                    .build());
        }
    }
}
