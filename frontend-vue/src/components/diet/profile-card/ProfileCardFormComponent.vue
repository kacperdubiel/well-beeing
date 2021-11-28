<template>
    <div class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false" id="profileCardFormModal" tabindex="-1" aria-labelledby="cardModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content card-form-modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title black-text ms2" id="cardModalLabel">Zmień dane karty</h5>
                        <button type="button" @click="clearStatus" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="weightInput">Waga [kg]*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="weightInput"
                                            type="number"
                                            min="1"
                                            max="999"
                                            v-model="this.formCardData.weight"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="heightInput">Wzrost [cm]*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="heightInput"
                                            type="number"
                                            min=1
                                            max=300
                                            v-model="this.formCardData.height"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="ageInput">Wiek [lata]*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="ageInput"
                                            type="number"
                                            min=1
                                            max=150
                                            v-model="this.formCardData.age"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text" for="activityTraining">Treningi [minuty/tydzień]*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            style="width: 60px;"
                                            id="activityTrainingInput"
                                            type="number"
                                            min=0
                                            v-model="this.formCardData.trainingActivity"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                </div>
                                <!--<hr/>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text form-check-label" for="veganInput">Dieta wegańska</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            id="veganInput"
                                            type="checkbox"
                                            v-model="this.formCardData.isVegan"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-8">
                                        <label class="black-text form-check-label" for="vegetarianInput">Dieta wegetariańska</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-4">
                                        <input
                                            id="vegetarianInput"
                                            type="checkbox"
                                            v-model="this.formCardData.isVegetarian"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                </div>-->
                                <hr/>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-7">
                                        <label class="black-text form-check-label" for="activityLevelInput">Poziom aktywności*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-5">
                                        <select id="activityLevelInput" @focus="clearStatus" @keypress="clearStatus" v-model="this.formCardData.activityLevel" class="form-select" aria-label="Default select example">
                                            <option value="VERY_LOW">{{this.$func_global.mapActivity("VERY_LOW")}}</option>
                                            <option value="LOW">{{this.$func_global.mapActivity("LOW")}}</option>
                                            <option value="MEDIUM">{{this.$func_global.mapActivity("MEDIUM")}}</option>
                                            <option value="HIGH">{{this.$func_global.mapActivity("HIGH")}}</option>
                                            <option value="VERY_HIGH">{{this.$func_global.mapActivity("VERY_HIGH")}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-7">
                                        <label class="black-text form-check-label" for="dietGoalInput">Cel*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-5">
                                        <select id="dietGoalInput" @focus="clearStatus" @keypress="clearStatus" v-model="this.formCardData.dietGoal" class="form-select" aria-label="Default select example">
                                            <option value="FAST_LOSE_WEIGHT">{{this.$func_global.mapDietGoal("FAST_LOSE_WEIGHT")}}</option>
                                            <option value="LOSE_WEIGHT">{{this.$func_global.mapDietGoal("LOSE_WEIGHT")}}</option>
                                            <option value="KEEP_WEIGHT">{{this.$func_global.mapDietGoal("KEEP_WEIGHT")}}</option>
                                            <option value="GAIN_WEIGHT">{{this.$func_global.mapDietGoal("GAIN_WEIGHT")}}</option>
                                            <option value="FAST_GAIN_WEIGHT">{{this.$func_global.mapDietGoal("FAST_GAIN_WEIGHT")}}</option>
                                            <option value="GAIN_MUSCLES">{{this.$func_global.mapDietGoal("GAIN_MUSCLES")}}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex;" class="col-md-7">
                                        <label class="black-text form-check-label" for="sexInput">Płeć*</label>
                                    </div>
                                    <div style="margin-bottom: 10px;" class="col-md-5">
                                        <select id="sexInput" @focus="clearStatus" @keypress="clearStatus" v-model="this.formCardData.sex" class="form-select" aria-label="Default select example">
                                            <option value="WOMAN">{{this.$func_global.mapSex("WOMAN")}}</option>
                                            <option value="MAN">{{this.$func_global.mapSex("MAN")}}</option>
                                        </select>
                                    </div>
                                </div>
                                <hr/>
                                <div class="row align-items-start">
                                    <div style="align-items: flex-start; display: flex; margin-bottom: 10px;">
                                        <label class="black-text form-check-label" for="sexInput">Dolegliwości i stan fizyczny</label>
                                    </div>
                                    <div style="margin-bottom: 10px;">
                                        <v-select
                                            class="px-0 py-2"
                                            multiple v-model="chosenAilmentsSource"
                                            :options="ailmentsSource"
                                            :reduce="name => name.id"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                            label="name"/>
                                    </div>
                                 </div>
                            </div>
                            <div style="justify-content: flex-end; flex-direction: row; display: flex;">
                                <button class="btn-card-panel-diet" @click="updateCardData">Zapisz</button>
                            </div>
                            <div v-if="this.successForm" class="alert alert-success" role="alert">
                                Zaktualizowano dane
                            </div>
                            <div v-if="this.errorForm" class="alert alert-danger" role="alert">
                                Upewnij się, że poprawnie uzupełniłeś oznaczone gwiazdką dane!
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "ProfileCardFormComponent",
    props:{
        formCardData: {
            type: Object
        },
        chosenAilmentsSource: {
            type: Array
        },
        ailmentsSource: {
            type: Array
        }
    },
    data(){
        return{
            submittingForm: false,
            successForm: false,
            errorForm: false
        }
    },
    methods: {
        clearStatus(){
            this.successForm = false,
            this.errorForm = false
        },
        updateCardData(){
            this.submittingForm = true
            this.clearStatus()
            if(this.invalidWeight() || this.invalidHeight() || this.invalidAge() || this.invalidTrainingActivity() || this.invalidActivityLevel() || this.invalidDietGoal() || this.invalidSex()){
                console.log(this.formCardData.weight)
                console.log(this.invalidWeight())
                this.errorForm = true
                return
            }
            let ailmentsObjectsArray = []
            this.chosenAilmentsSource.forEach((elem) => {ailmentsObjectsArray.push({id: (typeof elem != 'object') ? elem : elem.id})})
            this.formCardData.ailments = ailmentsObjectsArray
            axios({
                method: 'put',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')}, 
                url: "http://localhost:8090/profile-card",
                data: {
                    id: this.formCardData.id,
                    height: this.formCardData.height / 100,
                    weight: this.formCardData.weight,
                    age: this.formCardData.age,
                    ailments: this.formCardData.ailments,
                    activityLevel: this.formCardData.activityLevel,
                    trainingActivityTimePerWeek: this.formCardData.trainingActivity,
                    dietGoal: this.formCardData.dietGoal,
                    esex: this.formCardData.sex
                }
            })
            .then((response) => {this.$emit('save:card'); console.log("NO HALOLLLLO"); console.log(response); this.successForm = true})
            .catch(e => {this.errorForm = true;console.log(e);})
            this.submittingForm = false;
        },
        invalidWeight(){
            return this.formCardData.weight === '' || this.formCardData.weight <= 0.0
        },
        invalidHeight(){
            return this.formCardData.height === '' || this.formCardData.height <= 0.0
        },
        invalidAge(){
            return this.formCardData.age === '' || this.formCardData.age <= 0.0 
        },
        invalidTrainingActivity(){
            return this.formCardData.trainingActivity === '' || this.formCardData.trainingActivity < 0.0
        },
        invalidActivityLevel(){
            return this.formCardData.activityLevel === '' || (this.$func_global.mapActivity(this.formCardData.activityLevel) === 'Brak informacji')
        },
        invalidDietGoal(){
            return this.formCardData.dietGoal === '' || (this.$func_global.mapDietGoal(this.formCardData.dietGoal) === 'Brak informacji')
        },
        invalidSex(){
            return this.formCardData.sex === '' || (this.$func_global.mapSex(this.formCardData.sex) === 'Brak informacji')
        }
    },
}

</script>

<style scoped>
 .black-text {
     color: black;
 }
 .card-form-modal-content{
     background-color: white;
     color: black;
     border-radius: 15px;
     padding: 10px;
 }
</style>