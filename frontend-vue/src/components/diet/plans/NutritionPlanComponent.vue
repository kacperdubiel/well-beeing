<template>
    <div class="container-fluid">
    <div class="container-fluid">
        <div v-if="this.nutritionPlan.id != null" id="carouselExampleIndicators" class="carousel" data-bs-interval="false">
            <div class="carousel-inner">
                <div v-for="day in this.weekdays" :key="day" class="carousel-item" v-bind:class="{'active' : day=='MONDAY'}">
                    <div class="row">
                        <h4 class="text-center"><span>{{this.$func_global.mapDay(day)}} - </span>
                            <span>{{this.nutritionPlan.name}}</span>
                            <button v-if="!this.fromPost" @click="this.fetchPlanDayPositions(day)" class="btn-icon-panel-diet ms-4" data-bs-toggle="modal" data-bs-target="#planDayModal">
                                <font-awesome-icon :icon="['fa', 'info']"/>
                            </button>
                            <button v-if="!this.editOff && !this.fromPost" @click="this.changeComponentState" class="btn-icon-panel-diet">
                                <font-awesome-icon v-if="!this.isForm" :icon="['fa', 'edit']"/>
                                <font-awesome-icon v-else :icon="['fa', 'times']"/>
                            </button>
                            <button style="font-weight: bold; width: 100px;" v-if="!this.editOff && this.isForm && !this.fromDietician" class="btn-card-panel-diet" data-bs-toggle="modal" data-bs-target="#generatePlanModal">
                                Wygeneruj
                            </button>
                        </h4>
                    </div>
                    <div v-for="meal in meals" :key="meal" class="carousel-item-item-container">
                        <nutrition-plan-position-component @open:position="openDishModal" @delete:position="deletePosition" @add:position="changeAddParams" @positions:updated="onPositionsUpdated" :planId="this.nutritionPlan.id" :componentState="this.isForm" :id="meal + '_' + day" class="meal-item" style="width: 100%" :meal="meal" :day="day" :position="findPosition(day, meal)" :from-post="this.fromPost"/>
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
        <div v-if="this.nutritionPlan.id == null && !this.fromProfile && !fromDietician" class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                Stwórz swój własny główny plan dietetyczny lub wybierz jeden z aktualnych! 
        </div>
        <div v-if="this.nutritionPlanId == null && this.fromProfile" class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                Brak głównego planu dietetycznego 
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
                            <nutrition-plan-day-details-component :fromDieticianPlan="this.fromDietician" :fromDietician="this.fromProfile" :userId="this.userId" :positions="actualModalPositions"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="min-height: 700px;" id="addPositionModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="addPositionModalLabel" aria-hidden="false">
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
        <button id="openDishModal" hidden="true" data-bs-toggle="modal" data-bs-target="#dishPlanModal"/>
        <div id="dishPlanModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="dishPlanModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 v-if="this.modalPosition != null" style="color: black;" class="modal-title" id="dishPlanModalLabel">{{this.modalPosition.name}}</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <dish-component v-if="this.modalPosition != null" :dish="this.modalPosition"></dish-component>
                    </div>
                </div>
            </div>
        </div>
        <div id="generatePlanModal" data-bs-backdrop="static" data-bs-keyboard="false" class="modal fade" tabindex="-1" aria-labelledby="generatePlanModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 style="color: black;" class="modal-title" id="dishPlanModalLabel">Generator planu</h4>
                        <button id="generating-form-close" type="button" @click="clearGenerationStatusOnClose" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div v-if="!this.generationStarted">
                            <div style="flex-direction: column; display: flex; justify-content: center; text-align: start;">
                                <div class="form-check m-1">
                                    <input @click="this.dietGenerationChecked=false" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked>
                                    <label style="color: black;" class="form-check-label" for="flexRadioDefault1">
                                        Wykorzystaj tylko moje kalkulacje
                                    </label>
                                </div>
                                <div class="form-check m-1">
                                    <input v-model="this.dietGenerationChecked" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" :checked="true ? this.dietGenerationChecked : false">
                                    <label style="color: black;" class="form-check-label" for="flexRadioDefault2">
                                        Wykorzystaj gotowy typ diety
                                    </label>
                                </div>
                                <div v-if="this.dietGenerationChecked" class="mt-4">
                                    <p style="color: black;">
                                        Gotowy typ diety: 
                                    </p>
                                    <v-select
                                        id="dietPicker"
                                        v-model="this.chosenDiet"
                                        :options="this.diets"
                                        :reduce="name => name.id"
                                        label="name"/>
                                </div>
                                <button @click="generateNutritionPlan" style="align-self: flex-end;" class="btn-card-panel-diet mt-4">Wygeneruj</button>
                            </div>
                        </div>
                        <div v-else>
                            <p style="color: black;" v-if="!this.generationFinished"> trwa generowanie </p>
                            <div v-else>
                                <div v-if="this.generationSuccess" class="alert alert-success m-2" role="alert">
                                    Wygenenerowano plan!
                                </div>
                                <div v-if="this.generationError" class="alert alert-danger m-2" role="alert">
                                    Niestety w naszym systemie jest za mało danych, aby wygenerować idealny plan dla Ciebie! Spróbuj później :)
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
import axios from 'axios'
import DishBrowserComponent from '@/components/diet/dishes-browser/DishBrowserComponent.vue'
import DishComponent from '@/components/diet/dishes-browser/DishComponent.vue'
import NutritionPlanPositionComponent from "./NutritionPlanPositionComponent.vue"
import NutritionPlanDayDetailsComponent from "./NutritionPlanDayDetailsComponent.vue"
export default {
    name: "NutritionPlanComponent",
    props: {
        nutritionPlanId: {
            type: String,
        },
        editOff: {
            type: Boolean
        },
        fromProfile: {
            type: Boolean
        },
        fromDietician: {
            type: Boolean
        },
        userId: {
            type:String
        },
        fromPost: {
            type: Boolean
        },
    },
    watch: {
        nutritionPlanId: function () {
            if(this.nutritionPlanId != null)
                this.getSingleNutritionPlan(this.nutritionPlanId)
            else{
                this.nutritionPlan.id = null
            }
            this.isForm = false;
        }
    },
    components: {
        NutritionPlanPositionComponent,
        DishBrowserComponent,
        DishComponent,
        NutritionPlanDayDetailsComponent
    },
    mounted(){
        if(this.fromProfile || this.fromPost)
            this.getSingleNutritionPlan(this.nutritionPlanId)
        this.isForm = false;
        this.getDiets()
    },
    data(){
        return {
            weekdays: ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'],
            meals: ['BREAKFAST', 'LUNCH', 'DINNER', 'SNACK', 'SUPPER'],
            actualModalPositions: [],
            isForm: false,
            nutritionPlan: {
                id: 'def',
                nutritionPlanPositions: []
            },

            positionToAddDay: '',
            positionToAddMeal: '',

            addPositionError: false,
            deletePositionError: false,

            modalPosition: null,

            diets: [],
            chosenDiet: '',
            
            dietGenerationChecked: false,

            dataLoaded: false,

            generationStarted: false,
            generationFinished: false,
            generationSuccess: false,
            generationError: false
        }
    },
    methods: {  
        clearGeneratorStatus(){
            setTimeout(() => {
                this.dietGenerationChecked= false
                this.chosenDiet = ''
            }, 500)
        },
        clearGeneratorStatusStatus(){
            setTimeout(() => {
                this.generationStarted= false,
                this.generationFinished = false;
                this.generationSuccess = false;
                this.generationError = false;
            }, 500)
        },
        clearGenerationStatusOnClose(){
            this.clearGeneratorStatus()
            this.clearGeneratorStatusStatus()
        },
        openDishModal(dish){
            if(dish != null && !this.isForm){
                this.modalPosition = dish
                document.getElementById("openDishModal").click()
            }
        },   
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
            })
            .catch(e => {
                console.log(e);
            })
        },
        generateNutritionPlan(){
            this.generationStarted= false,
            this.generationFinished = false;
            this.generationSuccess = false;
            this.generationError = false;

            this.generationStarted = true
            let dietId;
            if(this.chosenDiet === ''){
                dietId = null
            }
            else{
                console.log('chosen diet' + this.chosenDiet)
                dietId = this.chosenDiet;
            }
            axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + this.$store.getters.getToken}, 
                url: `${this.apiURL}nutrition-plan/generate`,
                data: {
                    dietId: dietId,
                    nutritionPlanId: this.nutritionPlanId
                }
            })
            .then(response => {
                    console.log(response)
                    this.nutritionPlan = response.data
                    this.generationFinished = true;
                    this.generationSuccess = true;
                    this.getSingleNutritionPlan(response.data.id)
                }
            )
            .catch(e => {
                this.generationFinished = true;
                this.generationError = true;
                console.log(e);
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
        getDiets(){
            this.axios.get(`${this.apiURL}diet`,
                {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
            .then(response => {
                this.diets = response.data

            })
            .catch(e => {
                console.log(e);
            })
        }
    }
}
</script>

<style scoped>
    .carousel-item{
        width: 100%;
        height: 770px;
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