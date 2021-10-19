package com.wellbeeing.wellbeeing.service.diet.calculation;

import com.wellbeeing.wellbeeing.domain.account.ProfileCard;
import com.wellbeeing.wellbeeing.domain.diet.Ailment;
import com.wellbeeing.wellbeeing.domain.diet.type.EBasicMacro;

import java.util.HashMap;

public class BasicMacrosCalcStrategy implements MacrosCalcStrategy {

    private static double PROTEIN_GRAM_PER_WEIGHT_KILOGRAM_FOR_ADULT = 0.9;
    private static double PROTEIN_GRAM_PER_WEIGHT_ADDED_KILOGRAM_FOR_SENIOR = 0.3;
    private static double PROTEIN_GRAM_PER_WEIGHT_ADDED_KILOGRAM_FOR_TEEN = 0.1;
    private static int SENIOR_AGE = 60;
    private static int ADULT_AGE = 16;
    private static double FATS_NEED_BASIC_METABOLISM_PART = 0.25;

    private double countProteinsGramPerKilogramWeight(ProfileCard profileCard){
        double proteinPerWeightGram = PROTEIN_GRAM_PER_WEIGHT_KILOGRAM_FOR_ADULT;
        proteinPerWeightGram += profileCard.getDietGoal().getChangeInProteinsGramsPerKilogramWeight();
        for(Ailment a : profileCard.getAilments()){
            proteinPerWeightGram += a.getChangeInProteinsGramPerKilogram();
        }
        if(profileCard.getAge() < ADULT_AGE)
            proteinPerWeightGram += PROTEIN_GRAM_PER_WEIGHT_ADDED_KILOGRAM_FOR_TEEN;
        if(profileCard.getAge() > SENIOR_AGE)
            proteinPerWeightGram += PROTEIN_GRAM_PER_WEIGHT_ADDED_KILOGRAM_FOR_SENIOR;

        return proteinPerWeightGram;
    }

    @Override
    public HashMap<EBasicMacro, Double> calculateMacros(double completeMetabolism, ProfileCard profileCard) {
        double fatsKcal = completeMetabolism * FATS_NEED_BASIC_METABOLISM_PART;
        double fatsGram = fatsKcal / EBasicMacro.FATS.getKcalPerGram();
        double proteinsGramPerKilogramWeight = countProteinsGramPerKilogramWeight(profileCard);
        double proteinsGram = proteinsGramPerKilogramWeight * profileCard.getWeight();
        double proteinsKcal = proteinsGram * EBasicMacro.PROTEINS.getKcalPerGram();
        double carbohydratesKcal = completeMetabolism - fatsKcal - proteinsKcal;
        double carbohydratesGram = carbohydratesKcal / EBasicMacro.CARBOHYDRATES.getKcalPerGram();
        HashMap<EBasicMacro, Double> resultMacros = new HashMap<>();
        resultMacros.put(EBasicMacro.PROTEINS, proteinsGram);
        resultMacros.put(EBasicMacro.FATS, fatsGram);
        resultMacros.put(EBasicMacro.CARBOHYDRATES, carbohydratesGram);

        return resultMacros;
    }
}
