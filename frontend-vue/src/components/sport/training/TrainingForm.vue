<template>
    <div class="modal fade" id="addTrainingModal" tabindex="-1" aria-labelledby="addTrainingModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="addTrainingModalLabel">Dodawanie nowego treningu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs()"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid" id="modal-container">
                        <div class="col-11 mx-auto form-group">
                            <div class="row">
                                <p class="form-label">Nazwa</p>
                                <input
                                    type="text"
                                    placeholder="Nazwa treningu"
                                    v-model="name"
                                    id="name-form"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateTraining && invalidName || nameTaken}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row text-end">
                                <p v-if="nameTaken" class="has-error m-0">
                                    Istnieje już trening o takiej nazwie!
                                </p>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Trudność</p>
                                <select
                                    v-model="trainingDifficulty"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateTraining && invalidDifficultyLevel}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                >
                                    <option disabled value="">Wybierz trudność</option>
                                    <option>EASY</option>
                                    <option>MEDIUM</option>
                                    <option>HARD</option>
                                </select>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Opis</p>
                                <textarea
                                    placeholder=" Opis"
                                    v-model="description"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateTraining && invalidDescription}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Wskazówki</p>
                                <textarea
                                    placeholder=" Wskazówki"
                                    v-model="instruction"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateTraining && invalidInstruction}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div v-if="errorCreateTraining" class="row text-end">
                                <p class="has-error m-0">
                                    Proszę uzupełnić wszystkie dane poprawnie!
                                </p>
                            </div>
                            <div class="row justify-self-end mt-3">
                                <div class="col-12 d-flex justify-content-end">
                                    <button class="register-btn grey-btn py-2" @click="createTraining()">Zatwierdź i dodaj ćwiczenia</button>
                                </div>
                            </div>
<!--                        <div v-if="successRegister" class="col-11 mx-auto">-->
<!--                            <p>-->
<!--                                Rejestracja przebiegła pomyślnie!-->
<!--                            </p>-->
<!--                            <p>-->
<!--                                Możesz zalogować się swoimi danymi.-->
<!--                            </p>-->
<!--                        </div>-->

                    </div>
                        <div class="row mt-3">
                            <div class="col-12 text-black text-start">
                                Cwiczenia w treningu
                            </div>
                            <div class="col-12" >
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Nazwa</th>
                                        <th scope="col">Typ</th>
                                        <th scope="col">kcal/h</th>
                                        <th scope="col">Powt</th>
                                        <th scope="col">Serie</th>
                                        <th scope="col">Czas</th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr v-for="ex in this.createdTraining.exerciseInTrainings" :key="ex.exercise.exerciseId">
                                        <td>{{ ex.exercise.exerciseId }}</td>
                                        <td>{{ ex.exercise.name }}</td>
                                        <td>{{ ex.exercise.exerciseType }}</td>
                                        <td>{{ ex.exercise.caloriesBurned }}</td>
                                        <td>{{ ex.repetitions }}</td>
                                        <td>{{ ex.series }}</td>
                                        <td>{{ ex.time_seconds }}</td>
                                        <td>
                                            <button class="btn-white mx-2" @click="openInfoModal(ex.exercise.exerciseId)" data-bs-toggle="modal" data-bs-target="#infoExerciseModal">
                                                <font-awesome-icon :icon="['fa', 'info']" />
                                            </button>
                                            <button class="btn-white" v-if="enableButtons">
                                                <font-awesome-icon :icon="['fa', 'pencil-alt']" />
                                            </button>
                                            <button class="btn-white mx-2" v-if="enableButtons">
                                                <font-awesome-icon :icon="['fa', 'trash']" />
                                            </button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div v-if="showExercises" class="section-bg">
                            <table class="table table-hover">
                                <thead>
                                <tr class="text-start">
                                    <th scope="col" >Nazwa</th>
                                    <th scope="col">Powt</th>
                                    <th scope="col">Serie</th>
                                    <th scope="col">Czas</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="text-start">
                                            <span class="form-label white">
                                                {{currentExercise.exercise != null ? currentExercise.exercise.name : ''}}
                                            </span>
                                        </td>
                                        <td>
                                            <input
                                                type="number"
                                                v-model="currentExercise.repetitions"
                                                id="reps-form"
                                                class="register-input p-2 col-8 d-flex justify-self-start"
                                                :class="{ 'has-error': submittingCreateTraining }"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </td>
                                        <td>
                                            <input
                                                type="number"
                                                v-model="currentExercise.series"
                                                id="series-form"
                                                class="register-input p-2 col-8 d-flex justify-self-start"
                                                :class="{ 'has-error': submittingCreateTraining }"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </td>
                                        <td>
                                            <input
                                                type="number"
                                                v-model="currentExercise.time"
                                                id="time-form"
                                                class="p-2 col-8 d-flex justify-self-start"
                                                :class="{ 'has-error': submittingCreateTraining }"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </td>
                                        <td>
                                            <button class="register-btn btn-panel-sport p-2" @click="addExerciseToTraining(currentExercise.exercise.exerciseId)">+</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="row justify-content-end mt-3">
                                <div class="col-4">
                                    <button class="register-btn btn-panel-sport p-2" @click="setCurrent">Dodaj</button>
                                </div>
                            </div>
                            <div class="row my-2 align-items-center justify-content-end">
                                    <span class="col-2 float-end" v-bind:class="{'active-view': !this.isListView}" @click="setListView(false)">
                                        <font-awesome-icon  class="icon" :icon="['fa', 'th']" />
                                    </span>
                                <span class="col-2 float-end" v-bind:class="{'active-view': this.isListView}" @click="setListView(true)">
                                        <font-awesome-icon  class="icon" :icon="['fa', 'list-ul']" />
                                    </span>
                            </div>
                            <ExercisesListComponent :mode="'toTraining'" v-if="isListView" :exercises-source="exercisesSource"/>
                            <ExercisesGridComponent :mode="'toTraining'" v-if="!isListView" :exercises-source="exercisesSource"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <ExerciseDetails ref="detailsModali"/>
    </div>
</template>

<script>
import ExercisesListComponent from "@/components/sport/exercise/ExercisesListComponent";
import ExercisesGridComponent from "@/components/sport/exercise/ExercisesGridComponent";
import ExerciseDetails from "@/components/sport/exercise/ExerciseDetails";

export default {
    name: "TrainingForm",
    components: {
        ExercisesListComponent,
        ExercisesGridComponent,
        ExerciseDetails
    },
    data () {
        return {
            values: [],
            trainingId:-1,
            name: "",
            trainingDifficulty: "",
            met: 0.0,
            description: "",
            instruction: "",
            errorCreateTraining: false,
            successCreateTraining: false,
            nameTaken: false,
            submittingCreateTraining: false,
            repetitions:15,
            time:60,
            timeUnits:"min", //min, h, s
            series:1,
            currentExercise : {
                exercise: null,
                repetitions:20,
                time:30,
                timeUnits:"min", //min, h, s
                series:2,
            },
            createdTraining: {

            },
            showExercises:false,
            isListView: true,
            enableButtons: false
        }
    },
    props: {
        labelsSource: Object,
        exercisesSource: Object,
    },
    methods: {
        clearStatusWithTimeout() {
            setTimeout(() => {
                this.clearStatus()
            }, 500)
        },
        setCurrent() {
            this.currentExercise.exercise = this.exercisesSource.find(ex => ex.exerciseId === this.$store.getters.getExerciseToTrainingId)
        },
        openInfoModal(id) {
            this.$store.commit('setExerciseId', id);
            this.$refs.detailsModali.getExercise();
        },
        clearInputs() {
            this.name = ""
            this.type = ""
            this.met = 0.0
            this.description = ""
            this.instruction = ""
        },
        clearStatus() {
            this.errorCreateTraining = false
            this.successCreateTraining = false
            this.nameTaken = false
        },
        setListView(value) {
            console.log(value)
            this.isListView = value
        },
        createTraining() {
            console.log('name: ' + this.name + ' difficulty: ' + this.trainingDifficulty)
            this.submittingCreateTraining = true
            this.clearStatus()
            if (this.invalidName || this.invalidDifficultyLevel || this.invalidDescription || this.invalidInstruction) {
                this.errorCreateTraining = true
                return
            }
            const data = {
                    "name": this.name,
                    "trainingDifficulty": this.trainingDifficulty,
                    "description": this.description,
                    "instruction": this.instruction
            }


            const url = `${this.apiURL}sport/training`
            const token = this.$store.getters.getToken;
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.createdTraining = response.data
                this.successCreateTraining = true
                this.showExercises = true
                this.clearInputs()
                this.$emit('get:trainings')
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateTraining = false
            document.getElementsByClassName('')
        },
        addExerciseToTraining (exerciseId) {
            const url = `${this.apiURL}sport/training/${this.createdTraining.trainingId}/add-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            let time_seconds = 0;
            switch (this.currentExercise.timeUnits) {
                case "s":
                    time_seconds = this.currentExercise.time;
                    break;
                case "min":
                    time_seconds = this.currentExercise.time*60;
                    break;
                case "h":
                    time_seconds = this.currentExercise.time*3600;
                    break;
            }
            const data = {
                "reps":this.currentExercise.repetitions,
                "time_seconds":time_seconds,
                "series":this.currentExercise.series
            }
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.refreshCreatedTraining()
                // this.successCreateTraining = true
                // this.clearInputs()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
        },
        handleSubmit() {
            console.log('name: ' + this.name + ' type: ' + this.type + ' met: ' + this.met)
            this.submittingCreateTraining = true
            this.clearStatus()
            if (this.invalidName || this.invalidType || this.invalidMet || this.invalidDescription || this.invalidInstruction) {
                this.errorCreateTraining = true
                return
            }
            const data = {
                "exercise":{
                    "name": this.name,
                    "exerciseType": this.type,
                    "met":this.met,
                    "description": this.description,
                    "instruction": this.instruction
                },
                "labelsIds": this.values
            }


            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successCreateTraining = true
                this.clearInputs()
                this.$emit('get:exercises')
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateTraining = false
            document.getElementsByClassName('')
        },
        refreshCreatedTraining() {
            const url = `${this.apiURL}sport/training/${this.createdTraining.trainingId}`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.createdTraining = response.data
                this.$emit('get:trainings')
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
        }
    },
    computed: {
        invalidName() {
            return this.name === ''
        },
        invalidDifficultyLevel() {
            return this.trainingDifficulty === ''
        },
        invalidDescription() {
            return this.description === ''
        },
        invalidInstruction() {
            return this.instruction === ''
        }
    },
}
</script>

<style scoped>
.modal:nth-of-type(even) {
    z-index: 1062 !important;
}
.modal-backdrop.show:nth-of-type(even) {
    z-index: 1061 !important;
}
.pink-btn {
    background: var(--INTENSE-PINK);
}

.grey-btn {
    background: var(--GREY2);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

p.has-error {
    color: var(--INTENSE-PINK);
    /*font-weight: bold;*/
}
.modal-title {
    color: var(--SPORT);
}
.form-label {
    color: var(--GREY3);
    font-weight: bold;
    text-align: left;
    padding-left: 0;
}
.form-label.white {
     color: white;
 }
.register-btn {
    color: white;
    font-weight: bold;
}
.modal-dialog {
    max-width: 800px;
    width: 40%;
}
.active-view {
    color: var(--SPORT);
}
.modal-body .section-bg {
    border-radius: 2px;
}
</style>
