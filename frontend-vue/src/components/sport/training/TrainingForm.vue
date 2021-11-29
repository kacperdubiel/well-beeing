<template>
    <div id="addTrainingModal" aria-hidden="true" aria-labelledby="addTrainingModalLabel" class="modal fade"
         tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="addTrainingModalLabel" class="modal-title ms-2">Dodawanie nowego treningu</h5>
                    <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"
                            @click="clearInputs();clearStatus();"></button>
                </div>
                <div class="modal-body">
                    <div id="modal-container" class="container-fluid">
                        <div class="col-11 mx-auto form-group">
                            <div class="row">
                                <div class="col-6 ps-0 mt-3">
                                    <p class="form-label">Nazwa</p>
                                    <input
                                        id="name-form"
                                        v-model="name"
                                        :class="{ 'has-error': submittingCreateTraining && invalidName || nameTaken}"
                                        class="float-start register-input p-2"
                                        placeholder="Nazwa treningu"
                                        type="text"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <div class="col-6 ps-0 mt-3">
                                    <p class="form-label">Trudność</p>
                                    <select
                                        v-model="trainingDifficulty"
                                        :class="{ 'has-error': submittingCreateTraining && invalidDifficultyLevel}"
                                        class="float-start register-input p-2"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    >
                                        <option disabled value="">Wybierz trudność</option>
                                        <option v-for="difficulty in difficultyOptions" :key="difficulty"
                                                :value="difficulty">
                                            {{ this.$func_global.mapTrainingDifficulty(difficulty) }}
                                        </option>

                                    </select>
                                </div>

                            </div>
                            <div class="row text-end">
                                <p v-if="nameTaken" class="has-error m-0">
                                    Istnieje już trening o takiej nazwie!
                                </p>
                            </div>
                            <div class="row mt-3">

                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Opis</p>
                                <textarea
                                    v-model="description"
                                    :class="{ 'has-error': submittingCreateTraining && invalidDescription}"
                                    class="register-input p-2"
                                    placeholder=" Opis"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Wskazówki</p>
                                <textarea
                                    v-model="instruction"
                                    :class="{ 'has-error': submittingCreateTraining && invalidInstruction}"
                                    class="register-input p-2"
                                    placeholder=" Wskazówki"
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
                                    <button class="register-btn grey-btn py-2" @click="createTraining()">Zatwierdź i
                                        dodaj ćwiczenia
                                    </button>
                                </div>
                            </div>
                            <div class="row justify-self-end mt-3">
                                <div v-if="successCreateTraining" class="col-11 mx-auto submit-correct">
                                    <p>
                                        Dodano szkielet treningu!
                                    </p>
                                    <p>
                                        Teraz możesz dodać ćwiczenia.
                                    </p>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-12 text-black form-label">
                                    Cwiczenia w treningu
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div
                                    v-if="this.createdTraining.exerciseInTrainings != null &&  this.createdTraining.exerciseInTrainings.length !== 0"
                                    class="col-12">
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
                                        <tr v-for="ex in this.createdTraining.exerciseInTrainings"
                                            :key="ex.exercise.exerciseId">
                                            <td>{{ ex.exercise.exerciseId }}</td>
                                            <td>{{ ex.exercise.name }}</td>
                                            <td>{{ ex.exercise.exerciseType }}</td>
                                            <td>{{ ex.exercise.caloriesBurned }}</td>
                                            <td>{{ ex.repetitions }}</td>
                                            <td>{{ ex.series }}</td>
                                            <td>{{ ex.time_seconds }}</td>
                                            <td>
                                                <button class="btn-white mx-2"
                                                        data-bs-target="#infoExerciseModal"
                                                        data-bs-toggle="modal"
                                                        @click="openInfoModal(ex.exercise.exerciseId)">
                                                    <font-awesome-icon :icon="['fa', 'info']"/>
                                                </button>
                                                <button v-if="enableButtons" class="btn-white">
                                                    <font-awesome-icon :icon="['fa', 'pencil-alt']"/>
                                                </button>
                                                <button v-if="enableButtons" class="btn-white mx-2">
                                                    <font-awesome-icon :icon="['fa', 'trash']"/>
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div v-else class="col-12 text-black text-start form-label">
                                    <p>Brak</p>
                                </div>
                            </div>
                            <div v-if="showExercises" class="row mt-3 section-bg">

                                <div class="row mt-3 mx-3">
                                    <div class="col-12 form-label white">
                                        Cwiczenia do wyboru
                                    </div>
                                </div>
                                <div class="row mt-3 ">
                                    <div>
                                        <div class="row my-2 align-items-center justify-content-end">
                                            <span class="col-2 float-end"
                                                  v-bind:class="{'active-view': !this.isListView}"
                                                  @click="setListView(false)">
                                                <font-awesome-icon :icon="['fa', 'th']" class="icon"/>
                                            </span>
                                            <span class="col-2 float-end"
                                                  v-bind:class="{'active-view': this.isListView}"
                                                  @click="setListView(true)">
                                                <font-awesome-icon :icon="['fa', 'list-ul']" class="icon"/>
                                            </span>
                                        </div>
                                        <ExercisesListComponent v-if="isListView" :exercises-source="exercisesSource"
                                                                :mode="'toTraining'"/>
                                        <ExercisesGridComponent v-if="!isListView" :exercises-source="exercisesSource"
                                                                :mode="'toTraining'"/>

                                        <div class="row justify-content-center mt-3">
                                            <div class="col-4">
                                                <button class="register-btn btn-panel-sport p-2 mb-3"
                                                        @click="setCurrent">Dodaj
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-3 d-inline-flex align-content-center text-start  mb-3 white">
                                    <div class="col-3 align-content-end">
                                        <p class="form-label white">Nazwa</p>
                                        <p class="form-label text-start white">
                                            {{ currentExercise.exercise != null ? currentExercise.exercise.name : '' }}
                                        </p>
                                    </div>
                                    <div class="col-3 ">
                                        <label class="form-label white" for="reps-form">Powtórzenia</label>
                                        <input
                                            id="reps-form"
                                            v-model="currentExercise.repetitions"
                                            :class="{ 'has-error': submittingCreateTraining }"
                                            class="register-input col-10 p-1 justify-self-start"
                                            type="number"
                                            @focus="null"
                                            @keypress="null"
                                        />
                                    </div>
                                    <div class="col-2">
                                        <label class="form-label white" for="series-form">Serie</label>
                                        <input
                                            id="series-form"
                                            v-model="currentExercise.series"
                                            :class="{ 'has-error': submittingCreateTraining }"
                                            class="register-input p-1 col-8 d-flex justify-self-start"
                                            type="number"
                                            @focus="null"
                                            @keypress="null"
                                        />
                                    </div>
                                    <div class="col-3">
                                        <label class="form-label white" for="time-form">Czas [s]</label>
                                        <input
                                            id="time-form"
                                            v-model="currentExercise.time"
                                            :class="{ 'has-error': submittingCreateTraining }"
                                            class="p-1 col-8 d-flex justify-self-start"
                                            type="number"
                                            @focus="null"
                                            @keypress="null"
                                        />
                                    </div>
                                    <div class="col-1 d-flex">
                                        <button class="register-btn align-self-end btn-panel-sport h-auto p-2"
                                                @click="addExerciseToTraining(currentExercise.exercise.exerciseId)">+
                                        </button>
                                    </div>
                                </div>
                                <div class="row justify-content-center p-3">
                                    <div class="col-8">
                                        <button class="register-btn grey-btn p-2" data-bs-dismiss="modal"
                                                @click="clearInputs();clearStatus();">Zakończ dodawanie
                                        </button>
                                    </div>
                                </div>
                            </div>

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
    data() {
        return {
            difficultyOptions: ['EASY', 'MEDIUM', 'HARD'],
            values: [],
            trainingId: -1,
            name: "",
            trainingDifficulty: "",
            met: 0.0,
            description: "",
            instruction: "",
            errorCreateTraining: false,
            successCreateTraining: false,
            nameTaken: false,
            submittingCreateTraining: false,
            repetitions: 15,
            time: 60,
            timeUnits: "min", //min, h, s
            series: 1,
            currentExercise: {
                exercise: null,
                repetitions: 20,
                time: 30,
                timeUnits: "min", //min, h, s
                series: 2,
            },
            createdTraining: {},
            showExercises: false,
            isListView: true,
            enableButtons: false,
            isCreated: false
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
                this.isCreated = true
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
        addExerciseToTraining(exerciseId) {
            const url = `${this.apiURL}sport/training/${this.createdTraining.trainingId}/add-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            let time_seconds = 0;
            switch (this.currentExercise.timeUnits) {
                case "s":
                    time_seconds = this.currentExercise.time;
                    break;
                case "min":
                    time_seconds = this.currentExercise.time * 60;
                    break;
                case "h":
                    time_seconds = this.currentExercise.time * 3600;
                    break;
            }
            const data = {
                "reps": this.currentExercise.repetitions,
                "time_seconds": time_seconds,
                "series": this.currentExercise.series
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
                "exercise": {
                    "name": this.name,
                    "exerciseType": this.type,
                    "met": this.met,
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

input.has-error, textarea.has-error, select.has-error {
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

.active-view {
    color: var(--SPORT);
}

.modal-body .section-bg {
    border-radius: 2px;
}

.submit-correct {
    color: var(--TELEMEDIC);
}

input[type=number]::-webkit-inner-spin-button,
input[type=number]::-webkit-outer-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>
