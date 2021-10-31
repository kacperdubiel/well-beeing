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
    }
 }