<template>
    <div class="card-container">
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Waga </p>
            </div>
            <hr/>
            <div class="col-lg-8">
                <p  v-if="weight > 0" class="card-elem-container-row">{{weight}} kg. </p>
                <p  v-if="weight <= 0" class="card-elem-container-row">Brak informacji</p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Wzrost </p>
            </div>
            <div  class="col-lg-8">
                <p v-if="height > 0" class="card-elem-container-row">{{height}} cm. </p>
                <p v-if="height <= 0" class="card-elem-container-row"> Brak informacji </p>
            </div>
        </div>
        <div v-if="age > 0" class="card-elem-container">
           <div class="col-lg-8">
                <p class="card-elem-container-row">Wiek </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{age}} lat </p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Płeć </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{this.$func_global.mapSex(sex)}} </p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Dolegliwości i stany fizyczne</p>
            </div>
            <div class="col-lg-8">
                <ailment-row-component v-for="ailment_elem in ailments"
                :key="ailment_elem.id"
                :ailment="ailment_elem"
                @show:modal="showModal"
                class="card-elem-container-row"></ailment-row-component>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Dieta wegańska </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{this.$func_global.mapBoolean(isVegan)}} </p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Dieta wegetariańska </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{this.$func_global.mapBoolean(isVegetarian)}} </p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Poziom aktywności </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{this.$func_global.mapActivity(activityLevel)}} </p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Aktywność treningowa w tygodniu </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{trainingActivity}} minut </p>
            </div>
        </div>
        <div class="card-elem-container">
            <div class="col-lg-8">
                <p class="card-elem-container-row">Cel </p>
            </div>
            <div class="col-lg-8">
                <p class="card-elem-container-row">{{this.$func_global.mapDietGoal(dietGoal)}} </p>
            </div>
        </div>
        <div style="justify-content: flex-end; flex-direction: row; display: flex;">
            <button class="btn-card-panel-diet" data-bs-toggle="modal" data-bs-target="#profileCardFormModal">Edytuj dane</button>
        </div>
        <div id="ailmentModal" class="modal fade" tabindex="-1" aria-labelledby="ailmentModalLabel" aria-hidden="false">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-ailment-content">
                    <h3>{{modalData.name}}</h3>
                    <p style="font-size: 10pt; color: grey;">{{this.$func_global.mapAilmentType(modalData.type)}}</p>
                    <span style="text-align: left;">{{modalData.description}}</span>
                </div>
            </div>
        </div>
        <profile-card-form-component @save:card="updateCardData" :chosenAilmentsSource="this.ailments" :ailmentsSource="this.allAilments" :formCardData="dataToObject()"></profile-card-form-component>
    </div>
</template>

<script>
import axios from 'axios'
import AilmentRowComponent from './AilmentRowComponent.vue'
import ProfileCardFormComponent from './ProfileCardFormComponent.vue'
export default {
    name: "ProfileCardComponent",
    components:{
        AilmentRowComponent,
        ProfileCardFormComponent
    },
    data(){
        return {
            weight: 0,
            height: 0,
            age: 0,
            sex: '',
            ailments: [],
            isVegan: false,
            isVegetarian: false,
            activityLevel: '',
            trainingActivity: 0,
            dietGoal: '',
            modalData: Object,
            allAilments: []
        }
    },
    mounted(){
        this.getProfileCardData()
        this.getAilmentsData()
    },
    methods: {
        getProfileCardData(){
            axios.get('http://localhost:8090/profile-card', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                    this.weight = data.data.weight
                    this.height = data.data.height
                    this.age = data.data.age
                    this.sex = data.data.esex
                    this.isVegan = data.data.vegan
                    this.isVegetarian = data.data.vegetarian
                    this.activityLevel = data.data.activityLevel
                    this.trainingActivity = data.data.trainingActivityTimePerWeek
                    this.dietGoal = data.data.dietGoal
                    this.ailments = data.data.ailments
                }).catch(e => alert(e))
        },
        getAilmentsData(){
            axios.get('http://localhost:8090/ailment', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }
            })
            .then(data => {
                this.allAilments = data.data
            }).catch(e => alert(e))
        },
        updateCardData(profileCard){
            axios({
                method: 'put',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/profile-card",
                data: {
                    id: profileCard.id,
                    height: profileCard.height,
                    weight: profileCard.weight,
                    age: profileCard.age,
                    ailments: profileCard.ailments,
                    activityLevel: profileCard.activityLevel,
                    trainingActivityTimePerWeek: profileCard.trainingActivity,
                    dietGoal: profileCard.dietGoal,
                    vegan: profileCard.isVegan,
                    vegetarian: profileCard.isVegetarian,
                    esex: profileCard.sex
                }
            })
            .then((response) => {this.getProfileCardData(); console.log(response)})
            .catch(e => {console.log(e);})
        },
        showModal(ailment){
            this.modalData = ailment
        },

        dataToObject(){
            return {
                weight: this.weight,
                height: this.height,
                age: this.age,
                sex: this.sex,
                ailments: this.ailments,
                isVegan: this.isVegan,
                isVegetarian: this.isVegetarian,
                activityLevel: this.activityLevel,
                trainingActivity: this.trainingActivity,
                dietGoal: this.dietGoal,
            }
        }
    }
}
</script>

<style scoped>
    .card-elem-container {

        display: flex;
        border-top: 1pt solid gray;
    }
    .card-elem-container-row {
        align-items: flex-end;
        display: flex;
    }
    .card-container {
        width: 100%;
    }
    .modal-ailment-content {
       background-color: white;
       color: black;
       width: 100%;
       height: 100%;
       border-radius: 25px;
       display: flex;
       flex-direction: column;
       align-content: flex-start;
       align-items: flex-start;
       padding: 30px;
    }
</style>