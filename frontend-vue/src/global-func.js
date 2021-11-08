export const func_global = {
    mapSex(sex){
        if(sex == 'WOMAN')
            return "Kobieta"
        else if(sex == 'MAN')
            return "Mężczyzna"
        else
            return 'Brak informacji'
    },
    mapBoolean(state){
        if(state)
            return "Tak"
        else
            return "Nie"
    },
    mapActivity(activity){
        if(activity == 'VERY_LOW')
            return "Bardzo niska"
        else if(activity == "LOW")
            return "Niska"
        else if(activity == "MEDIUM")
            return "Średnia"
        else if(activity == "HIGH")
            return "Wysoka"
        else if(activity == "VERY_HIGH")
            return "Bardzo wysoka"
        else
            return "Brak informacji"
    },
    mapDietGoal(goal){
        if(goal == 'FAST_LOSE_WEIGHT')
            return "Szybka utrata wagi"
        else if(goal == "LOSE_WEIGHT")
            return "Utrata wagi"
        else if(goal == "KEEP_WEIGHT")
            return "Utrzymanie wagi"
        else if(goal == "GAIN_WEIGHT")
            return "Przybranie na wadze"
        else if(goal == "FAST_GAIN_WEIGHT")
            return "Szybkie przybranie na wadze"
        else if(goal == "GAIN_MUSCLES")
            return "Budowa masy mięśniowej"
        else
            return "Brak informacji"
    },
    mapAilmentType(ailmentType){
        if(ailmentType == 'ALLERGY')
            return "Alergia"
        else if(ailmentType == "ILLNESS")
            return "Choroba"
        else if(ailmentType == "INJURY")
            return "Kontuzja"
        else if(ailmentType == "PHYSICAL_CONDITION")
            return "Stan fizyczny"
        else
            return "Brak informacji"
    },
    mapBMIResult(result){
        if(result == 'SEVERELY_UNDERWEIGHT')
            return "Wychudzenie"
        else if(result == 'UNDERWEIGHT')
            return "Niedowaga"
        else if(result == "HEALTHY")
            return "Waga w normie"
        else if(result == "OVERWEIGHT")
            return "Nadwaga"
        else if(result == "OBESE")
            return "Otyłość"
        else
            return "Nieznany"
    },
    mapMeal(meal){
        if(meal == 'BREAKFAST')
            return "ŚNIADANIE"
        else if(meal == 'LUNCH')
            return "LUNCH"
        else if(meal == "DINNER")
            return "OBIAD"
        else if(meal == "SNACK")
            return "PRZEKĄSKA"
        else if(meal == "SUPPER")
            return "KOLACJA"
        else
            return "Nieznany"
    },
    mapGlycemicIndex(index){
        if(index == 'LOW')
            return "Niski"
        else if(index == 'MEDIUM')
            return "Średni"
        else if(index == "HIGH")
            return "Wysoki"
        else if(index == "ANY_RECOMMENDED")
            return "Niski/średni"
        else if(index == "ANY")
            return "Dowolny"
        else
            return "Nieznany"
    },
    mapMeasure(measure){
        if(measure == 'GRAM')
            return "g"
        else if(measure == 'MILLI_GRAM')
            return "mg"
        else if(measure == "MICRO_GRAM")
            return "mcg"
        else if(measure == "KILO_GRAM")
            return "kg"
        else
            return "Nieznana"
    },
    proteinCalories(){
        return 4
    },
    fatCalories(){
        return 9
    },
    carbCalories(){
        return 4
    }
 }