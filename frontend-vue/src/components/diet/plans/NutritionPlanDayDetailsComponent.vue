<template>
    <div>
        <p style="color: black;">{{this.details}}</p>
        <p style="color: black;">{{this.calculations}}</p>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "NutritionPlanDayDetailsComponent",
    props: {
        positions: []
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
            calculations: null
        }
    },
    mounted(){
        this.countDetails()
        this.getProfileCalculations()
    },
    methods: {
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
        },
        getProfileCalculations(){
            axios.get('http://localhost:8090/diet-calc', {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                this.calculations = response.data
            })
            .catch(e => alert(e))
        }
    }
}
</script>

<style scoped>

</style>