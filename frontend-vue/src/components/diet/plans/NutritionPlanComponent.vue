<template>
    <div class="container-fluid">
        <div v-if="this.nutritionPlan != null" id="carouselExampleIndicators" class="carousel" data-bs-interval="false">
            <div class="carousel-inner">
                <div v-for="day in this.weekdays" :key="day" class="carousel-item" v-bind:class="{'active' : day=='MONDAY'}">
                    <div class="row">
                        <h4>{{day}}
                            <button @click="this.fetchPlanDayPositions(day)" class="btn-icon-panel-diet" data-bs-toggle="modal" data-bs-target="#planDayModal">
                                <font-awesome-icon :icon="['fa', 'info']"/>
                            </button>
                            <button @click="this.changeComponentState" class="btn-icon-panel-diet">
                                <font-awesome-icon v-if="!this.isForm" :icon="['fa', 'edit']"/>
                                <font-awesome-icon v-else :icon="['fa', 'times']"/>
                            </button>
                            <span>{{this.nutritionPlan.name}}</span>
                        </h4>
                    </div>
                    <div v-for="meal in meals" :key="meal" class="carousel-item-item-container">
                        <nutrition-plan-position-component @delete:position="deletePosition" @add:position="changeAddParams" @positions:updated="onPositionsUpdated" :planId="this.nutritionPlan.id" :componentState="this.isForm" :id="meal + '_' + day" class="meal-item" style="width: 100%" :meal="meal" :day="day" :position="findPosition(day, meal)"/>
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
        <div v-else class="alert alert-danger alert-dismissible fade show" role="alert">
                Nie posiadasz jeszcze żadnych planów treningowych!
        </div>
        <div v-if="deletePositionError" class="alert alert-danger alert-dismissible fade show" role="alert">
                Błąd połączenia, nie udało się usunąć pozycji.
                <button @click="this.deletePositionError=false" type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
        </div>
        <div id="planDayModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="planDayModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="planDayModalLabel">Szczegóły</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div>
                            <p style="color: black;">{{actualModalPositions}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="addPositionModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="addPositionModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black;" class="modal-title" id="addPositionModalLabel">Dodaj pozycję</h5>
                        <button @click="clearStatus" type="button" id="close-add-position-modal" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <dish-browser-component @position:chosen="this.addPosition" :fromNutritionPlan="true"></dish-browser-component>
                        <div v-if="this.addPositionError" class="alert alert-danger mt-3 mx-3" role="alert">
                                Błąd dodawania pozycji! 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import DishBrowserComponent from '@/components/diet/DishBrowserComponent.vue'
import NutritionPlanPositionComponent from "./NutritionPlanPositionComponent.vue"
export default {
    name: "NutritionPlanComponent",
    props: {
        nutritionPlanId: {
            type: Object,
        }
    },
    watch: {
        nutritionPlanId: function () {
            this.getSingleNutritionPlan(this.nutritionPlanId)
        }
    },
    components: {
        NutritionPlanPositionComponent,
        DishBrowserComponent
    },
    data(){
        return {
            weekdays: ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'],
            meals: ['BREAKFAST', 'LUNCH', 'DINNER', 'SNACK', 'SUPPER'],
            actualModalPositions: [],
            isForm: false,
            nutritionPlan: {
                nutritionPlanPositions: []
            },

            positionToAddDay: '',
            positionToAddMeal: '',

            addPositionError: false,
            deletePositionError: false
        }
    },
    methods: {      
        changeAddParams(day, meal){
            this.positionToAddDay = day
            this.positionToAddMeal = meal
        },
        clearStatus(){
            this.addPositionError = false;
        },
        changeComponentState(){
            this.isForm = !this.isForm
        },
        findPosition(day, meal){
            let result = null
            this.nutritionPlan.nutritionPlanPositions.forEach(elem => {
                if(elem.weekDay === day && elem.mealType === meal){
                    result = elem
                }   
            })
            return result;
        },
        fetchPlanDayPositions(day){
            let res = []
            this.nutritionPlan.nutritionPlanPositions.forEach(elem => {
                if(elem.weekDay === day){
                    res.push(elem)
                }
            })
            this.actualModalPositions = res
            console.log(this.actualModalPositions)
        },
        onPositionsUpdated(nutritionPlan){
            this.getSingleNutritionPlan(nutritionPlan.id)
        },
        getSingleNutritionPlan(planId){
            this.axios.get(`${this.apiURL}nutrition-plan/${planId}`,
                {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                console.log(response)
                this.nutritionPlan = response.data
                //return response.data
            })
            .catch(e => {
                console.log(e);
                //return plan;
            })
        },
        closeAddPositionModal(){
            document.getElementById('close-add-position-modal').click();
        },
        deletePosition(id){
            console.log('usuwam')
            console.log(id)
            axios({
                method: 'delete',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}nutrition-plan/${this.nutritionPlan.id}/position/${id}`,
            })
            .then(response => {
                    console.log(response)
                    this.getSingleNutritionPlan(response.data.id)
                }
            )
            .catch(e => {
                console.log(e);
            })
        },
        addPosition(dish){
            console.log('dodaje')
            console.log(dish)
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}nutrition-plan/${this.nutritionPlan.id}/position`,
                data: {
                    dish: {
                        id: dish.id
                    },
                    mealType: this.positionToAddMeal,
                    weekDay: this.positionToAddDay
                }
            })
            .then(response => {
                    console.log(response)
                    this.getSingleNutritionPlan(response.data.id)
                    this.closeAddPositionModal()
                }
            )
            .catch(e => {
                console.log(e);
                this.addPositionError = true;
            })
        },
    }
}
</script>

<style scoped>
    .carousel-item{
        width: 100%;
        height: 620px;
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
    h4 > button{
        width: 30px;
        height: 30px;
        font-weight: lighter;
        font-size: medium;
    }
</style>