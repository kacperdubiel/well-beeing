<template>
    <div>
        <div class="modal fade" id="infoTrainingModal" tabindex="-1" aria-labelledby="infoTrainingModalLabel" aria-hidden="true" >
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title ms-2" id="infoTrainingModalLabel">Szczegóły treningu</h5>
                        <button class="btn-white" @click="editTraining()">
                            <font-awesome-icon :icon="['fa', 'pencil-alt']" />
                        </button>
                        <button type="button" class="btn-close" id="infoTrainingModalClose" ref="infoTrainingModalClose"  data-bs-dismiss="modal" aria-label="Close" @click="clearInputs()"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid" id="modal-container">
                            <div class="col-11 mx-auto form-group" v-if="training != null">
                                <div class="row">
                                    <div class="col-xl-5 col-md-12 px-0 ">
                                        <p class="form-label">Nazwa</p>
                                        <p class="info-value" v-if="!edit">{{training.name}}</p>
                                        <input
                                            v-if="this.edit"
                                            type="text"
                                            placeholder="Nazwa treningu"
                                            v-model="editedTraining.name"
                                            id="name-form"
                                            class="register-input w-100 p-2 float-start"
                                            :class="{ 'has-error': submittingEditTraining && invalidName || nameTaken}"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                        <p v-if="nameTaken && edit" class="has-error m-0">
                                            Istnieje już trening o takiej nazwie!
                                        </p>
                                    </div>
                                    <div class="col-xl-4 col-md-12 px-0">
                                        <p class="form-label">Trudność</p>
                                        <p class="info-value" v-if="!edit">{{this.$func_global.mapTrainingDifficulty(training.trainingDifficulty)}}</p>
                                        <select
                                            v-model="editedTraining.trainingDifficulty"
                                            v-if="this.edit"
                                            class="register-input p-2 float-start"
                                            :class="{ 'has-error': submittingEditTraining && invalidDifficulty}"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        >
                                            <option disabled value="">Wybierz trudność</option>
                                            <option v-for="difficulty in difficultyOptions" :key="difficulty" :value="difficulty">{{this.$func_global.mapTrainingDifficulty(difficulty)}}</option>

                                        </select>
                                    </div>
                                    <div class="col-xl-3 col-md-12 px-0" v-if="!edit">
                                        <p class="form-label">kcal</p>
                                        <p class="info-value">{{training.caloriesBurned}}</p>
                                    </div>
                                </div>
                                <div class="row mt-3" v-if="!edit">
                                    <p class="form-label">Etykiety</p>
                                    <div class="container labels-container px-1 py-1" v-if="training.labels != null && training.labels.length > 0">
                                        <div class="form-label label-node p-2 mx-1 my-1"
                                             :style="{backgroundColor: randomColor(label.sportLabelId)}"
                                             v-for="label in training.labels" :key="label.sportLabelId">{{label.name}}</div>

                                    </div>
                                    <p v-else class="info-value">Brak etykiet</p>
                                </div>
                                <div class="row mt-3" v-if="!edit">
                                    <p class="form-label">Czas trwania</p>
                                    <p class="info-value">{{this.$func_global.getTimePrettyFromSeconds(training.totalTrainingTimeSeconds)}}</p>
                                </div>

                                <div class="row mt-3">
                                    <p class="form-label">Opis</p>
                                    <p class="info-value" v-if="!edit">{{training.description}}</p>
                                    <textarea
                                        placeholder=" Opis"
                                        v-if="this.edit"
                                        v-model="editedTraining.description"
                                        class="register-input p-2"
                                        :class="{ 'has-error': submittingEditTraining && invalidDescription}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <div class="row mt-3">
                                    <p class="form-label">Instrukcja</p>
                                    <p class="info-value" v-if="!edit">{{training.instruction}}</p>
                                    <textarea
                                        placeholder=" Instrukcja"
                                        v-if="this.edit"
                                        v-model="editedTraining.instruction"
                                        class="register-input p-2"
                                        :class="{ 'has-error': submittingEditTraining && invalidInstruction}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <!--EXERCISES IN TRAINING-->
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <table class=" table table-hover">
                                            <caption class="caption-top form-label">Cwiczenia w treningu</caption>
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id</th>
                                                    <th scope="col">Nazwa</th>
                                                    <th scope="col">Typ</th>
                                                    <th scope="col">kcal</th>
                                                    <th scope="col">Powt</th>
                                                    <th scope="col">Serie</th>
                                                    <th scope="col">Czas</th>
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <template v-for="ex in (this.edit ? this.editedTraining.exerciseInTrainings : this.training.exerciseInTrainings)" :key="ex.exercise.exerciseId">
                                                    <tr class="accordion accordion-toggle" id="accordionExample"
                                                        v-bind:class="{'opened': opened.includes(ex.exercise.exerciseId)}">
                                                        <td>{{ ex.exercise.exerciseId }}</td>
                                                        <td>{{ ex.exercise.name }}</td>
                                                        <td>{{ this.$func_global.mapExerciseType(ex.exercise.exerciseType) }}</td>
                                                        <td>{{ ex.caloriesBurned }}</td>
                                                        <td>{{ ex.repetitions }}</td>
                                                        <td>{{ ex.series }}</td>
                                                        <td>{{ ex.time_seconds }}</td>
                                                        <td>
                                                            <button class="btn-white mx-2" @click="toggle(ex.exercise.exerciseId)">
                                                                <font-awesome-icon :icon="opened.includes(ex.exercise.exerciseId) ? ['fa', 'chevron-up'] : ['fa', 'chevron-down']" />
                                                            </button>
                                                            <button v-if="edit" class="btn-white mx-2" @click="removeExercise(ex)">
                                                                <font-awesome-icon :icon="['fa', 'trash']" />
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    <tr :key="ex.name" v-if="opened.includes(ex.exercise.exerciseId)" class="sub-table">
                                                        <td colspan="8">
                                                            <div class="container-fluid" v-if="ex.exercise != null">
                                                                <div class="col-11 mx-auto form-group">
                                                                    <div class="row mt-3">
                                                                        <p class="form-label">Etykiety</p>
                                                                        <div class="container labels-container px-1 py-1" v-if="ex.exercise.labels != null && ex.exercise.labels.length > 0">
                                                                            <div class="form-label label-node p-2 mx-1 my-1"
                                                                                 :style="{backgroundColor: randomColor(label.sportLabelId)}"
                                                                                 v-for="label in ex.exercise.labels" :key="label.sportLabelId">{{label.name}}</div>

                                                                        </div>
                                                                        <p v-else class="info-value">Brak etykiet</p>

                                                                    </div>
                                                                    <div class="row mt-3">
                                                                        <p class="form-label">Wydatek energetyczny (intensywność)</p>
                                                                        <p class="info-value">{{ex.exercise.met}} METs</p>
                                                                    </div>

                                                                    <div class="row mt-3">
                                                                        <p class="form-label">Opis</p>
                                                                        <p class="info-value">{{ex.exercise.description}}</p>
                                                                    </div>
                                                                    <div class="row mt-3 ">
                                                                        <p class="form-label ">Instrukcja</p>
                                                                        <p class="info-value" v-html="ex.exercise.instruction"></p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </template>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div class="row mt-3 section-bg" v-if="edit">

                                    <div class="row mt-3 mx-3">
                                        <div class="col-12 form-label white">
                                            Cwiczenia do wyboru
                                        </div>
                                    </div>
                                    <div class="row mt-3 ">
                                        <div>
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

                                            <div class="row justify-content-center mt-3">
                                                <div class="col-4">
                                                    <button class="register-btn btn-panel-sport p-2 mb-3" @click="setCurrent">Dodaj</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mt-3 d-inline-flex align-content-center text-start  mb-3 white">
                                        <div class="col-3 align-content-end">
                                            <p class="form-label white">Nazwa</p>
                                            <p class="form-label text-start white">
                                                {{currentExercise.exercise != null ? currentExercise.exercise.name : ''}}
                                            </p>
                                        </div>
                                        <div class="col-3 ">
                                            <label for="reps-form" class="form-label white">Powtórzenia</label>
                                            <input
                                                type="number"
                                                v-model="currentExercise.repetitions"
                                                id="reps-form"
                                                class="register-input col-10 p-1 justify-self-start"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </div>
                                        <div class="col-2">
                                            <label for="series-form" class="form-label white">Serie</label>
                                            <input
                                                type="number"
                                                v-model="currentExercise.series"
                                                id="series-form"
                                                class="register-input p-1 col-8 d-flex justify-self-start"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </div>
                                        <div class="col-3">
                                            <label for="time-form" class="form-label white">Czas [s]</label>
                                            <input
                                                type="number"
                                                v-model="currentExercise.time_seconds"
                                                id="time-form"
                                                class="p-1 col-8 d-flex justify-self-start"
                                                @focus="null"
                                                @keypress="null"
                                            />
                                        </div>
                                        <div class="col-1 d-flex">
                                            <button class="register-btn align-self-end btn-panel-sport h-auto p-2" @click="addExerciseToTraining(currentExercise)">+</button>
                                        </div>
                                    </div>
                                </div>

                                <div v-if="errorEditTraining" class="row text-end">
                                    <p class="has-error m-0">
                                        Proszę uzupełnić wszystkie dane poprawnie!
                                    </p>
                                </div>
                                <div class="row mt-3 justify-content-between" v-if="edit">
                                    <div class="col-4 ">
                                        <button class="grey-btn h-100 text-white float-start" @click="cancelEditTraining()">
                                            Anuluj edycję
                                        </button>
                                    </div>
                                    <div class="col-4">
                                        <button class="btn-panel-sport" @click="submitEditTraining()">
                                            Zapisz zmiany
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div v-else>
                                NIE MA NIC
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ExercisesListComponent from "@/components/sport/exercise/ExercisesListComponent";
import ExercisesGridComponent from "@/components/sport/exercise/ExercisesGridComponent";

export default {
    name: "TrainingDetails",
    components:{
        ExercisesListComponent,
        ExercisesGridComponent
    },
    data () {
        return {
            difficultyOptions: ['EASY','MEDIUM','HARD'],
            edit: false,
            values: [],
            name: "",
            type: "",
            trainingDifficulty: 'EASY',
            description: "",
            instruction: "",
            errorEditTraining: false,
            successEditTraining: false,
            nameTaken: false,
            submittingEditTraining: false,
            labelStyle: {
                backgroundColor:this.randomColor()
            },
            opened: [],
            editedTraining: Object,
            editedRemovedExercises: [],
            editedAddedExercises: [],
            exercisesSource:[],
            currentExercise : {
                exercise: null,
                repetitions:20,
                time_seconds:30,
                timeUnits:"m", //min, h, s
                series:2,
            },
            createdTraining: {

            },
            showExercises:false,
            isListView: true,
            enableButtons: false,
            isCreated: false,
            submittingCreateTraining: false
        }
    },
    props: {
        trainingId: Number,
        training: Object
    },
    methods: {
        submitEditTraining(){
            console.log('name: ' + this.name + ' difficulty: ' + this.trainingDifficulty)
            this.submittingCreateTraining = true
            this.clearStatus()
            if (this.invalidName || this.invalidDifficultyLevel || this.invalidDescription || this.invalidInstruction) {
                this.errorCreateTraining = true
                console.log('ERROR')
                return
            }
            const data = {
                "name": this.editedTraining.name,
                "trainingDifficulty": this.editedTraining.trainingDifficulty,
                "description": this.editedTraining.description,
                "instruction": this.editedTraining.instruction
            }


            const url = `${this.apiURL}sport/training/${this.editedTraining.trainingId}`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.editedRemovedExercises.forEach(e => {
                    this.removeExerciseFromTrainingById(this.editedTraining.trainingId, e.exercise.exerciseId)
                })
                this.editedAddedExercises.forEach(e => {
                    this.addExerciseToTrainingById(this.editedTraining.trainingId, e.exercise.exerciseId, e)
                })
                this.createdTraining = response.data
                this.successCreateTraining = true
                this.isCreated = true
                this.clearInputs()
                this.$emit('get:trainings') // WRÓCIC
                this.$refs.infoTrainingModalClose.click()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateTraining = false
            document.getElementsByClassName('')
        },
        setCurrent() {
            this.currentExercise.exercise = this.exercisesSource.find(ex => ex.exerciseId === this.$store.getters.getExerciseToTrainingId)
        },
        addExerciseToTraining(exercise) {
            const newExercise = Object.assign({},exercise)
            newExercise.time_seconds = exercise.time_seconds*60
            newExercise.caloriesBurned = ((newExercise.time_seconds*exercise.series)/60*(exercise.exercise.met*3.5*80/200))
            this.editedTraining.exerciseInTrainings.push(newExercise)
            this.editedAddedExercises.push(newExercise)
        },
        removeExercise(exercise){
            const index = this.editedTraining.exerciseInTrainings.findIndex(ex => ex === exercise)
            if (index > -1)
            {
                this.editedTraining.exerciseInTrainings.splice(index,1)
                this.editedRemovedExercises.push(exercise)
            }
        },
        addExerciseToTrainingById(trainingId, exerciseId, exercise) {
            const url = `${this.apiURL}sport/training/${trainingId}/add-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            let time_seconds = 0;
            switch (this.currentExercise.timeUnits) {
                case "s":
                    time_seconds = exercise.time_seconds;
                    break;
                case "min":
                    time_seconds = exercise.time_seconds*60;
                    break;
                case "h":
                    time_seconds = exercise.time_seconds*3600;
                    break;
            }
            const data = {
                "reps":exercise.repetitions,
                "time_seconds":time_seconds,
                "series":exercise.series
            }
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.refreshCreatedTraining()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
        },
        removeExerciseFromTrainingById(trainingId, exerciseId) {
            const url = `${this.apiURL}sport/training/${trainingId}/remove-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            const data = {}
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
        cancelEditTraining() {
            this.editedTraining = {}
            this.edit = false
        },
        setListView(value) {
            console.log(value)
            this.isListView = value
        },
        editTraining() {
            this.editedTraining = Object.assign({},this.training)
            this.editedTraining.exerciseInTrainings = Object.assign([],this.training.exerciseInTrainings)
            this.getExercises()
            // console.log('Array Labels ',this.values)
            this.edit = true
        },
        toggle(id) {
            const index = this.opened.indexOf(id);
            if (index > -1) {
                this.opened.splice(index, 1)
            } else {
                this.opened.push(id)
            }
        },
        async getTraining () {
            const url = `${this.apiURL}sport/training/${this.$store.getters.getTrainingId}`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.training = response.data
                console.log(this.exercises)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getExercises () {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercisesSource = response.data['content']
            }).catch(error => {
                console.log(error.response);
            });
        },
        clearInputs() {
            this.name = ""
            this.type = ""
            this.trainingDifficulty = 'EASY'
            this.description = ""
            this.instruction = ""
            this.values = []
        },
        clearStatus() {
            this.errorEditTraining = false
            this.successEditTraining = false
            this.nameTaken = false
        },
        randomColor(seed) {
            let availableColors = ['#C33149', '#FEA12A', '#08415C', '#0E9594', '#8FB339', '#90E39A', '#96E6B3', '#5386E4', '#585123', '#802392']
            return availableColors[seed % availableColors.length];
            // return `rgb(${r()}, ${r()}, ${r()})`;
        }
    },
    computed: {
        invalidName() {
            return this.editedTraining.name === ''
        },
        invalidDifficultyLevel() {
            return this.editedTraining.trainingDifficulty === '';
        },
        invalidDescription() {
            return this.editedTraining.description === ''
        },
        invalidInstruction() {
            return this.editedTraining.instruction === ''
        }
    }
}
</script>

<style scoped>
table {
    table-layout:fixed;
    width:100%;
}
.opened {
    background-color: rgba(0, 0, 0, 0.075);
}
.sub-table {
    background-color: rgba(0, 0, 0, 0.075);
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
.info-value {
    color: var(--GREY3);
    text-align: left;
    padding-left: 0;
}
.register-btn {
    color: white;
    font-weight: bold;
}
.labels-container {
    border: solid;
    border-color: var(--GREY1);
    border-radius: 5px;
    border-width: 2px;
    display: flex;
    flex-wrap: wrap;
}
.label-node {
    border-radius: 10px;
    color: var(--bs-white);
    font-size: x-small;
    font-weight: normal;
}

.grey-btn {
    background: var(--GREY2);
}
</style>
