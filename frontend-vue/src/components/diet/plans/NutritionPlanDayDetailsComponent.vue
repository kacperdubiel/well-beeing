<template>
    <div v-if="calculationsLoaded && detailsLoaded" class="container-fluid">
        <div v-if="!fromDieticianPlan">
            <h5 v-if="this.calculations != '' && this.positionsToShow.length != 0" class="mb-4" style="color: black; text-align: start;">Kalkulacje</h5>
            <div class="mb-4" v-if="this.calculations != '' && this.positionsToShow.length != 0">
                <div class="m-2">
                    <p style="color: black; text-align: start;">Kalorie {{ Math.round(this.details.calories*100)/100 }} / {{Math.round(this.calculations.suggestedCalories)}} kcal</p>
                    <progress-bar-component 
                    :measure="'kcal'" 
                    :actualValue="Math.round(this.details.calories*100)/100" 
                    :expectedValue="Math.round(this.calculations.suggestedCalories)"/>
                </div>
                <div class="m-2">
                    <p style="color: black; text-align: start;">Węglowodany {{ Math.round(this.details.carbs*100)/100 }} / {{Math.round(this.calculations.suggestedCarbohydrates)}} g</p>
                    <progress-bar-component 
                    :measure="'g'" 
                    :actualValue="Math.round(this.details.carbs*100)/100" 
                    :expectedValue="Math.round(this.calculations.suggestedCarbohydrates)"/>
                </div>
                <div class="m-2">
                    <p style="color: black; text-align: start;">Tłuszcze {{ Math.round(this.details.fats*100)/100 }} / {{Math.round(this.calculations.suggestedFats)}} g</p>
                    <progress-bar-component 
                    :measure="'g'" 
                    :actualValue="Math.round(this.details.fats * 100)/100" 
                    :expectedValue="Math.round(this.calculations.suggestedFats)"/>
                </div>
                <div class="m-2">
                    <p style="color: black; text-align: start;">Białka {{ Math.round(this.details.proteins*100)/100 }} / {{Math.round(this.calculations.suggestedProteins)}} g</p>
                    <progress-bar-component 
                    :measure="'g'" 
                    :actualValue="Math.round(this.details.proteins * 100)/100" 
                    :expectedValue="Math.round(this.calculations.suggestedProteins)"/>
                </div>
            </div>
            <table v-if="this.calculations != '' && this.positionsToShow.length != 0" style="color: var(--GREY3);" class="table table-hover  mt-4">
                <tr>
                    <th style="text-align: start;" class="col-sm-3" scope="col">Posiłek</th>
                    <th class="col-sm-4" scope="col">Aktualna kaloryczność [kcal]</th>
                    <th class="col-sm-5" scope="col">Sugerowana kaloryczność [kcal]</th>
                </tr>
                <tbody>
                    <tr v-for="meal in this.$func_global.mealsList()" :key="meal">
                        <th style="text-align: start;" class="col-sm-3" scope="row">{{this.$func_global.mapMeal(meal)}}</th>
                        <td class="col-sm-4">{{this.findActualPositionCalc(meal)}}</td>
                        <td class="col-sm-5">{{this.findSuggestedPositionCalc(meal)}}</td>
                    </tr>
                </tbody>
            </table>
            <table v-if="this.calculations != '' && this.positionsToShow.length != 0" style="color: var(--GREY3);" class="table table-hover mt-4">
                <tr>
                    <th style="text-align: start;" class="col-sm-3" scope="col">Posiłek</th>
                    <th class="col-sm-4" scope="col">Aktualny IG</th>
                    <th class="col-sm-5" scope="col">Sugerowany IG</th>
                </tr>
                <tbody>
                    <tr v-for="meal in this.$func_global.mealsList()" :key="meal">
                        <th style="text-align: start;" class="col-sm-3" scope="row">{{this.$func_global.mapMeal(meal)}}</th>
                        <td class="col-sm-4">{{this.$func_global.mapGlycemicIndex(this.findActualPositionIg(meal))}}</td>
                        <td class="col-sm-5">{{this.$func_global.mapGlycemicIndex(this.findSuggestedPositionIg(meal))}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <h5 class="mt-4 mb-4" style="color: black; text-align: start;">Podsumowanie</h5>
        <div style="align-items: center; display: flex; width:100%;">
            <table class="table table-hover">
                <tbody>
                    <tr>
                        <th>Kalorie</th>
                        <td>{{Math.round(details.calories*100)/100}} kcal</td>
                    </tr>
                    <tr>
                        <th class="col-lg-6">Białka</th>
                        <td>{{Math.round(details.proteins*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Węglowodany</th>
                        <td>{{Math.round(details.carbs*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Cukry</th>
                        <td>{{Math.round(details.sugars*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Tłuszcze</th>
                        <td>{{Math.round(details.fats*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Tłuszcze nasycone</th>
                        <td>{{Math.round(details.satFats*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Cholesterol</th>
                        <td>{{Math.round(details.cholesterol*1000)/1000}} g</td>
                    </tr>
                    <tr>
                        <th>Błonnik</th>
                        <td>{{Math.round(details.fiber*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Sól</th>
                        <td>{{Math.round(details.salt*100)/100}} g</td>
                    </tr>
                    <tr>
                        <th>Kofeina</th>
                        <td>{{Math.round(details.caffeine*1000)/1000}} g</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import ProgressBarComponent from '../ProgressBarComponent.vue'
export default {
    name: "NutritionPlanDayDetailsComponent",
    props: {
        positions: [],
        fromDietician: {
            type: Boolean
        },
        userId: {
            type: String
        },
        fromDieticianPlan: {
            type: Boolean
        }
    },
    watch: {
        positions: function () {
            this.positionsToShow = this.positions
            this.countDetails()
        }
    },
    data(){
        return{
            positionsToShow: [],
            details: null,
            calculations: null,
            detailsLoaded: false,
            calculationsLoaded: false
        }
    },
    components: {
        ProgressBarComponent
    },
    mounted(){
        this.countDetails()
        this.getProfileCalculations()
    },
    methods: {
        findSuggestedPositionCalc(meal){
            let result;
            if(this.calculations.dietCalcMealSuggestions != null){
                this.calculations.dietCalcMealSuggestions.forEach(elem => {
                if (elem.mealType === meal){
                    result = Math.round(elem.numberOfCalories*100)/100
                }
            })
            }
            return result;
        },
        findActualPositionCalc(meal){
            let result;
            this.positionsToShow.forEach(elem => {
                if (elem.mealType === meal){
                    result = Math.round(elem.dish.derivedNutritionalValues.derivedCalories*100)/100
                }
            })
            return result;
        },
        findSuggestedPositionIg(meal){
            let result;
            if(this.calculations.dietCalcMealSuggestions != null)
                this.calculations.dietCalcMealSuggestions.forEach(elem => {
                    if (elem.mealType === meal){
                        result = elem.glycemicIndexLevel
                    }
                })
            return result;
        },
        findActualPositionIg(meal){
            let result;
            this.positionsToShow.forEach(elem => {
                if (elem.mealType === meal){
                    result = elem.dish.glycemicIndexLevel
                }
            })
            return result;
        },
        countDetails(){
            this.details = {
                calories: 0,
                proteins: 0,
                carbs: 0,
                sugars: 0,
                fats: 0,
                satFats: 0,
                cholesterol: 0,
                fiber: 0,
                salt: 0,
                caffeine: 0
            }
            this.positionsToShow.forEach(position => {
                this.details.calories = this.details.calories + position.dish.derivedNutritionalValues.derivedCalories
                this.details.proteins = this.details.proteins + position.dish.derivedNutritionalValues.derivedProteins
                this.details.carbs = this.details.carbs + position.dish.derivedNutritionalValues.derivedCarbohydrates
                this.details.sugars = this.details.sugars + position.dish.derivedNutritionalValues.derivedSugar
                this.details.fats = this.details.fats + position.dish.derivedNutritionalValues.derivedFats
                this.details.satFats = this.details.satFats + position.dish.derivedNutritionalValues.derivedSaturatedFats
                this.details.cholesterol = this.details.cholesterol + position.dish.derivedNutritionalValues.derivedCholesterol
                this.details.fiber = this.details.fiber + position.dish.derivedNutritionalValues.derivedFiber
                this.details.caffeine = this.details.caffeine + position.dish.derivedNutritionalValues.derivedCaffeine
                this.details.salt = this.details.salt + position.dish.derivedNutritionalValues.derivedSalt      
            })
            this.detailsLoaded = true;
        },
        getOwnProfileCalculations(){
            axios.get('http://localhost:8090/diet-calc', {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                this.calculations = response.data
                this.calculationsLoaded = true;
            })
            .catch(e => alert(e))
        },
        getOtherProfileCalculations(){
            axios.get('http://localhost:8090/diet-calc/profile/' + this.userId, {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                this.calculations = response.data
                this.calculationsLoaded = true;
            })
            .catch(e => alert(e))
        },
        getProfileCalculations(){
            if(this.fromDietician && this.userId != null){
                this.getOtherProfileCalculations()
            }
            else
                this.getOwnProfileCalculations()
        }
    }
}
</script>

<style scoped>

</style>