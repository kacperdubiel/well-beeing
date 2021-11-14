<template>
    <div >
        <div style="width: 100%; text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">CWICZENIA</h6>
            </div>
            <div class="row">
                <div class="col-lg-5">
                    <v-select style="margin: 0px; padding: 0px;" @keypress="this.getExercisesToSelect" v-model="this.actualSelectedExercise" :options="this.exercisesToSelect" :reduce="name => name.exerciseId" label="name" />
                </div>
                <div class="col-lg-1">
                    <input
                        style="width: 60px;"
                        id="productReportInput"
                        type="number"
                        min=0
                        v-model="this.actualSelectedExerciseTime"
                    />
                </div>
                <div class="col-lg-2">
                    <select id="measureProductInput" v-model="this.actualSelectedExerciseMeasure" class="form-select" aria-label="Product measure">
                        <option value="SEC">s</option>
                        <option value="MIN">min</option>
                        <option value="H">h</option>
                    </select>
                </div>
                <div class="col-lg-2">
                    <input v-model="this.actualSelectedExerciseExercisingTime" id="productTime" type="time"/>
                </div>
                <div class="col-lg-2">
                    <button @click="this.addExerciseToReport" class="add-button"><font-awesome-icon :icon="['fa', 'plus-circle']"/></button>
                </div>
            </div>
            <hr/>
            <div>
                <table class="table table-borderless table-hover">
                    <thead>
                        <tr>
                            <th class="col-lg-6" scope="col">Cwiczenie</th>
                            <th class="col-lg-1" scope="col">Czas trwania</th>
                            <th class="col-lg-2" scope="col">Spalone kalorie</th>
                            <th class="col-lg-2" scope="col">Godzina</th>
                            <th class="col-lg-1" scope="col"> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="exercise in this.actualReport.exerciseList" :key="exercise.id">
                            <th scope="row">{{exercise.exercise.name}}</th>
                            <td>{{this.$func_global.getTimePrettyFromSeconds(exercise.seconds)}}</td>
                            <td>{{Math.round(exercise.caloriesBurned)}}</td>
                            <td>{{exercise.exercisingTime.substring(11, 16)}}</td>
                            <td><button @click="this.deleteExerciseFromReport(exercise.id)" class="btn-white"><font-awesome-icon :icon="['fa', 'trash']"/></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
<!--        <div style="width: 100%; text-align: left;">-->
<!--            <hr class="hr-dish title-line"/>-->
<!--            <div class="title" style="align-items: flex-start; display: flex">-->
<!--                <h6 class="title">DANIA</h6>-->
<!--            </div>-->
<!--            <div class="row">-->
<!--                <div class="col-lg-5">-->
<!--                    <v-select @keypress="this.getDishesToSelect" v-model="this.actualSelectedDish" :options="this.dishesToSelect" :reduce="name => name.id" label="name" />-->
<!--                </div>-->
<!--                <div class="col-lg-1">-->
<!--                    <input-->
<!--                        style="width: 60px;"-->
<!--                        id="dishReportInput"-->
<!--                        type="number"-->
<!--                        min=1-->
<!--                        v-model="this.actualSelectedDishPortions"-->
<!--                    />-->
<!--                </div>-->
<!--                <div class="col-lg-2">-->
<!--                </div>-->
<!--                <div class="col-lg-2">-->
<!--                    <input v-model="this.actualSelectedDishConsumingTime" id="productTime2" type="time"/>-->
<!--                </div>-->
<!--                <div class="col-lg-2">-->
<!--                    <button @click="this.addDishToReport" class="add-button"><font-awesome-icon :icon="['fa', 'plus-circle']"/></button>-->
<!--                </div>-->
<!--            </div>-->
<!--            <hr/>-->
<!--            <div>-->
<!--                <table class="table table-borderless table-hover">-->
<!--                    <thead>-->
<!--                        <tr>-->
<!--                            <th class="col-lg-6" scope="col">Danie</th>-->
<!--                            <th class="col-lg-1" scope="col">Porcje</th>-->
<!--                            <th class="col-lg-2" scope="col"></th>-->
<!--                            <th class="col-lg-2" scope="col">Godzina</th>-->
<!--                            <th class="col-lg-1" scope="col"></th>-->
<!--                        </tr>-->
<!--                    </thead>-->
<!--                    <tbody>-->
<!--                        <tr v-for="detailDish in this.actualReport.dishDetailsList" :key="detailDish.id">-->
<!--                            <th scope="row">{{detailDish.dish.name}}</th>-->
<!--                            <td>{{detailDish.portions}}</td>-->
<!--                            <td></td>-->
<!--                            <td>{{detailDish.consumingTime.substring(11, 16)}}</td>-->
<!--                            <td><button @click="this.deleteDishFromReport(detailDish.id)" class="btn-icon-panel-diet"><font-awesome-icon :icon="['fa', 'trash']"/></button></td>-->
<!--                        </tr>-->
<!--                    </tbody>-->
<!--                </table>-->
<!--            </div>-->
<!--        </div>-->
    </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: "SportReportFormComponent",
    props: {
        report: {
            type: Object
        }
    },
    data(){
        return{
            actualReport: Object,

            exercisesToSelect: [],
            actualSelectedExercise: '',
            actualSelectedExerciseTime: 3600,
            actualSelectedExerciseMeasure: 's',
            actualSelectedExerciseExercisingTime: '12:00',

            trainingsToSelect: [],
            actualSelectedTraining: '',
            actualSelectedTrainingTime: 3600,
            actualSelectedTrainingMeasure: 's',
            actualSelectedTrainingExercisingTime: '12:00',
            // productsToSelect: [],
            // actualSelectedProduct: '',
            // actualSelectedProductAmount: 100,
            // actualSelectedProductMeasure: '',
            // actualSelectedProductConsumingTime: '12:00',
            //
            // dishesToSelect: [],
            // actualSelectedDish: '',
            // actualSelectedDishPortions: 1,
            // actualSelectedDishConsumingTime: '12:00',
        }
    },
    watch: {
        report: function () {
            this.getCurrentReport()
        }
    },
    mounted() {
        // this.getProductsToSelect()
        // this.getDishesToSelect()
        this.getExercisesToSelect()
        this.getTrainingsToSelect()
    },
    methods: {
        getExercisesToSelect(){
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log("SPR")
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            .then(response => {
                    this.exercisesToSelect = response.data['content']
            }).catch(e => alert(e))
        },
        getTrainingsToSelect(){
            const url = `${this.apiURL}sport/training`
            const token = this.$store.getters.getToken;
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            .then(response => {
                    console.log("POBIERAM TRENINGI DO SELECTA")
                    console.log(response.data)
                    this.trainingsToSelect = response.data['content']
            }).catch(e => alert(e))
        },
        addExerciseToReport(){
            const url = `${this.apiURL}sport/report/${this.report.id}/exercise`
            const token = this.$store.getters.getToken;
            let data= [{
                exercise: {
                    exerciseId: this.actualSelectedExercise
                },
                seconds: this.actualSelectedExerciseTime,
                exercisingTime: this.makeConsumingTimestamp(this.actualSelectedExerciseExercisingTime)
            }]
            axios.post(url,data, {headers: {Authorization: `Bearer ${token}`}})
                .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
                .catch(e => {console.log(e);})
        }, // TRAINING
        addTrainingToReport(){
            const url = `${this.apiURL}sport/report/${this.report.id}/training`
            const token = this.$store.getters.getToken;
            let data= [{
                training: {
                    trainingId: this.actualSelectedTraining
                },
                seconds: this.actualSelectedTrainingTime,
                exercisingTime: this.makeConsumingTimestamp(this.actualSelectedTrainingExercisingTime)
            }]
            axios.post(url,data, {headers: {Authorization: `Bearer ${token}`}})
                .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
                .catch(e => {console.log(e);})
        },
        addDishToReport(){
           axios({
                method: 'post',
                headers: {Authorization: 'Bearer ' + localStorage.getItem('token')},
                url: "http://localhost:8090/report/" + this.report.id + "/dish",
                data: [{
                    dish: {
                        id: this.actualSelectedDish
                    },
                    portions: this.actualSelectedDishPortions,
                    consumingTime: this.makeConsumingTimestamp(this.actualSelectedDishConsumingTime)
                }]
            })
            .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
            .catch(e => {console.log(e);})
        },
        deleteExerciseFromReport(id){
            const url = `${this.apiURL}sport/report/${this.report.id}/exercise`
            const token = this.$store.getters.getToken;
            axios.delete(url, {headers: {Authorization: `Bearer ${token}`}, data: [id]})
                .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
                .catch(e => {console.log(e);})
        },
        deleteTrainingFromReport(id){
            const url = `${this.apiURL}sport/report/${this.report.id}/training`
            const token = this.$store.getters.getToken;
            axios.delete(url, {headers: {Authorization: `Bearer ${token}`}, data: [id]})
                .then((response) => {this.getCurrentReport(); console.log(response); this.$emit('updated:report', response.data);})
                .catch(e => {console.log(e);})
        },
        getCurrentReport(){
            const url = `${this.apiURL}sport/report/${this.report.id}`
            const token = this.$store.getters.getToken;
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            .then((response) => {console.log(response); this.actualReport = response.data})
            .catch(e => {console.log(e);})
        },
        makeConsumingTimestamp(consumingTime){
            let ts = this.report.reportDate + 'T' + consumingTime + ':00'
            return ts;
        }
    }
}
</script>

<style scoped>
    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 95%;
        margin-top: 10px;
        background-color:var(--GREY1);
        height: 1px;
    }
    .title-line {
        margin-bottom: 0px
    }

    .title {
        color: var(--GREY1);
        margin-top: 2px;
        margin-bottom: 5px;
        font-size: 10pt;
    }

    .table-report-info {
        color: var(--GREY2);
        border-style: solid;
        border-color: var(--DIET);
        margin-top: 10px;
    }

    .add-button{
        color: var(--DIET);
        background-color: transparent;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        padding: 0px;
        margin: 0;
        border: none;
        font-size: 2rem;
        font-weight: bold;
    }
</style>
