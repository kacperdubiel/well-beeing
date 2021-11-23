<template>
    <div class="container-fluid">
        <div id="carouselExampleIndicators" class="carousel" data-bs-interval="false">
            <div class="carousel-inner">
                <div v-for="day in this.weekdays" :key="day" class="carousel-item" v-bind:class="{'active' : day=='MONDAY'}">
                    <h4>{{day}}</h4>
                    <div v-for="meal in meals" :key="meal" class="carousel-item-item-container">
                        <nutrition-plan-position-component :id="meal + '_' + day" class="meal-item" style="width: 100%" :meal="meal" :day="day" :position="findPosition(day, meal)"/>
                    </div>
                </div>
            </div>
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="monday"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="tuesday"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="wednesday"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="thursday"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4" aria-label="friday"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="5" aria-label="saturday"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="6" aria-label="sunday"></button>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
</template>

<script>
import NutritionPlanPositionComponent from "./NutritionPlanPositionComponent.vue"
export default {
    name: "NutritionPlanComponent",
    props: {
        nutritionPlan: {
            type: Object,
        }
    },
    watch: {
        nutritionPlan: function () {
            console.log("plan changed")
        }
    },
    components: {
        NutritionPlanPositionComponent
    },
    data(){
        return {
            weekdays: ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'],
            meals: ['BREAKFAST', 'LUNCH', 'DINNER', 'SNACK', 'SUPPER'],
        }
    },
    methods: {
        findPosition(day, meal){
            let result = null
            this.nutritionPlan.nutritionPlanPositions.forEach(elem => {
                if(elem.weekDay === day && elem.mealType === meal){
                    result = elem
                }   
            })
            return result;
        }
    }
}
</script>

<style scoped>
    .carousel-item{
        width: 100%;
        height: 600px;
        padding: 20px;
        align-items: center;
        justify-content: center;
    }
    .carousel-item-item-container{
        justify-content: center;
        display: flex;
        align-content: center;
        flex-direction: row;
        border-radius: 10px;
        margin: 10px;
    }
    .meal-item {
        width: 100%;
        align-self: center;
        justify-content: center;
        align-items: center;
        flex-direction: row;
        display: flex;
    }
    
</style>