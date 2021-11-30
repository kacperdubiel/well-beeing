<template>
    <div>
        <div style="text-align: left;">
            <hr class="hr-dish title-line"/>
            <div class="title" style="align-items: flex-start; display: flex">
                <h6 class="title">CWICZENIA</h6>
            </div>
            <div class="row">
                <div class="col-4">
                    <v-select v-model="this.actualSelectedExercise" :options="this.exercisesToSelect"
                              :reduce="name => name.exerciseId" class="sport"
                              label="name"
                              @keypress="this.getExercisesToSelect"/>
                </div>
                <div class="col-2">
                    <input
                        id="productReportInput"
                        v-model="this.actualSelectedExerciseTime"
                        class="w-100 h-75"
                        min=0
                        type="number"
                    />
                </div>
                <div class="col-2">
                    <select id="measureProductInput" v-model="this.actualSelectedExerciseMeasure"
                            aria-label="Product measure"
                            class="form-select w-100">
                        <option value="s">s</option>
                        <option value="min">min</option>
                        <option value="h">h</option>
                    </select>
                </div>
                <div class="col-2">
                    <input id="productTime" v-model="this.actualSelectedExerciseExercisingTime" class="w-100 h-75"
                           type="time"/>
                </div>
                <div class="col-2">
                    <button class="add-button" @click="this.addExerciseToReport">
                        <font-awesome-icon :icon="['fa', 'plus-circle']"/>
                    </button>
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
                        <th class="col-lg-1" scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="exercise in this.actualReport.exerciseList" :key="exercise.id">
                        <th scope="row">{{ exercise.exercise.name }}</th>
                        <td>{{ this.$func_global.getTimePrettyFromSeconds(exercise.seconds) }}</td>
                        <td>{{ Math.round(exercise.caloriesBurned) }}</td>
                        <td>{{ exercise.exercisingTime.substring(11, 16) }}</td>
                        <td>
                            <button class="btn-white" @click="this.deleteExerciseFromReport(exercise.id)">
                                <font-awesome-icon :icon="['fa', 'trash']"/>
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div style="width: 100%; text-align: left;">
                <hr class="hr-dish title-line"/>
                <div class="title" style="align-items: flex-start; display: flex">
                    <h6 class="title text-capitalize">Treningi</h6>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <v-select v-model="this.actualSelectedTraining" :options="this.trainingsToSelect"
                                  :reduce="name => name.trainingId"
                                  class="sport" label="name" @keypress="this.getTrainingsToSelect"/>
                    </div>
                    <div class="col-lg-2">
                        <input
                            id="trainingReportInput"
                            v-model="this.actualSelectedTrainingTime"
                            class="w-100 h-75"
                            min=1
                            type="number"
                        />
                    </div>
                    <div class="col-lg-2">
                        <select id="measureProductInput" v-model="this.actualSelectedTrainingMeasure"
                                aria-label="Product measure" class="form-select w-100">
                            <option value="s">s</option>
                            <option value="min">min</option>
                            <option value="h">h</option>
                        </select>
                    </div>
                    <div class="col-lg-2">
                        <input id="productTime2" v-model="this.actualSelectedTrainingExercisingTime" class="w-100 h-75"
                               type="time"/>
                    </div>
                    <div class="col-lg-2">
                        <button class="add-button" @click="this.addTrainingToReport">
                            <font-awesome-icon :icon="['fa', 'plus-circle']"/>
                        </button>
                    </div>
                </div>
                <hr/>
                <div>
                    <table class="table table-borderless table-hover">
                        <thead>
                        <tr>
                            <th class="col-lg-6" scope="col">Trening</th>
                            <th class="col-lg-1" scope="col">Czas trwania</th>
                            <th class="col-lg-2" scope="col">Spalone kalorie</th>
                            <th class="col-lg-2" scope="col">Godzina</th>
                            <th class="col-lg-1" scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="training in this.actualReport.trainingList" :key="training.id">
                            <th scope="row">{{ training.training.name }}</th>
                            <td>{{ this.$func_global.getTimePrettyFromSeconds(training.seconds) }}</td>
                            <td>{{ Math.round(training.caloriesBurned) }}</td>
                            <td>{{ training.trainingTime.substring(11, 16) }}</td>
                            <td>
                                <button class="btn-white" @click="this.deleteTrainingFromReport(training.id)">
                                    <font-awesome-icon :icon="['fa', 'trash']"/>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
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
    data() {
        return {
            actualReport: Object,

            exercisesToSelect: [],
            actualSelectedExercise: '',
            actualSelectedExerciseTime: 3600,
            actualSelectedExerciseMeasure: 'min',
            actualSelectedExerciseExercisingTime: '12:00',

            trainingsToSelect: [],
            actualSelectedTraining: '',
            actualSelectedTrainingTime: 3600,
            actualSelectedTrainingMeasure: 'min',
            actualSelectedTrainingExercisingTime: '12:00',
        }
    },
    watch: {
        report: function () {
            this.getCurrentReport()
        }
    },
    mounted() {
        this.getExercisesToSelect()
        this.getTrainingsToSelect()
    },
    methods: {
        getProperTimeInSeconds(time, units) {
            let time_seconds = 0;
            switch (units) {
                case "s":
                    time_seconds = time;
                    break;
                case "min":
                    time_seconds = time * 60;
                    break;
                case "h":
                    time_seconds = time * 3600;
                    break;
            }
            return time_seconds
        },
        getExercisesToSelect() {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            const myParams = {
                size: 500,
            }
            axios.get(url, {
                params: myParams,
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
                .then(response => {
                    this.exercisesToSelect = response.data['content']
                }).catch(e => alert(e))
        },
        getTrainingsToSelect() {
            const url = `${this.apiURL}sport/training`
            const token = this.$store.getters.getToken;
            const myParams = {
                size: 500,
            }
            axios.get(url, {
                params: myParams,
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
                .then(response => {
                    this.trainingsToSelect = response.data['content']
                }).catch(e => alert(e))
        },
        addExerciseToReport() {
            const url = `${this.apiURL}sport/report/${this.report.id}/exercise`
            const token = this.$store.getters.getToken;
            let data = [{
                exercise: {
                    exerciseId: this.actualSelectedExercise
                },
                seconds: this.getProperTimeInSeconds(this.actualSelectedExerciseTime, this.actualSelectedExerciseMeasure),
                exercisingTime: this.makeConsumingTimestamp(this.actualSelectedExerciseExercisingTime)
            }]
            axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}})
                .then((response) => {
                    this.getCurrentReport();
                    console.log(response);
                    this.$emit('updated:report', response.data);
                })
                .catch(e => {
                    console.log(e);
                })
        }, // TRAINING
        addTrainingToReport() {
            const url = `${this.apiURL}sport/report/${this.report.id}/training`
            const token = this.$store.getters.getToken;
            let data = [{
                training: {
                    trainingId: this.actualSelectedTraining
                },
                seconds: this.getProperTimeInSeconds(this.actualSelectedTrainingTime, this.actualSelectedTrainingMeasure),
                trainingTime: this.makeConsumingTimestamp(this.actualSelectedTrainingExercisingTime)
            }]
            axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}})
                .then((response) => {
                    this.getCurrentReport();
                    console.log(response);
                    this.$emit('updated:report', response.data);
                })
                .catch(e => {
                    console.log(e);
                })
        },
        deleteExerciseFromReport(id) {
            const url = `${this.apiURL}sport/report/${this.report.id}/exercise`
            const token = this.$store.getters.getToken;
            axios.delete(url, {headers: {Authorization: `Bearer ${token}`}, data: [id]})
                .then((response) => {
                    this.getCurrentReport();
                    console.log(response);
                    this.$emit('updated:report', response.data);
                })
                .catch(e => {
                    console.log(e);
                })
        },
        deleteTrainingFromReport(id) {
            const url = `${this.apiURL}sport/report/${this.report.id}/training`
            const token = this.$store.getters.getToken;
            axios.delete(url, {headers: {Authorization: `Bearer ${token}`}, data: [id]})
                .then((response) => {
                    this.getCurrentReport();
                    console.log(response);
                    this.$emit('updated:report', response.data);
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getCurrentReport() {
            const url = `${this.apiURL}sport/report/${this.report.id}`
            const token = this.$store.getters.getToken;
            axios.get(url, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
                .then((response) => {
                    console.log(response);
                    this.actualReport = response.data
                })
                .catch(e => {
                    console.log(e);
                })
        },
        makeConsumingTimestamp(consumingTime) {
            let ts = this.report.reportDate + 'T' + consumingTime + ':00'
            return ts;
        }
    }
}
</script>

<style scoped>
.hr-dish {
    border: 0;
    clear: both;
    display: block;
    width: 95%;
    margin-top: 10px;
    background-color: var(--GREY1);
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
    border-color: var(--SPORT);
    margin-top: 10px;
}

.add-button {
    color: var(--SPORT);
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

::-webkit-scrollbar-thumb {
    border-radius: 5px;
    background-color: var(--SPORT);
    -webkit-box-shadow: inset 0 0 6px rgba(90, 90, 90, 0.7);
}
</style>
