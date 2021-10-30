<template>
    <div class="modal fade" id="addExerciseModal" tabindex="-1" aria-labelledby="addExerciseModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="addExerciseModalLabel">Dodawanie nowego treningu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs(); clearStatusWithTimeout()"></button>
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
                            <div class="row justify-content-end mt-3">
                                <div class="col-4">
                                    <button class="register-btn grey-btn p-2" @click="clearInputs(); clearStatusWithTimeout()" data-bs-dismiss="modal">Anuluj</button>
                                </div>
                                <div class="col-4">
                                    <button class="register-btn btn-panel-sport p-2" @click="createTraining">Dodaj</button>
                                </div>
                            </div>

                        </div>

                        <div v-if="successRegister" class="col-11 mx-auto">
                            <p>
                                Rejestracja przebiegła pomyślnie!
                            </p>
                            <p>
                                Możesz zalogować się swoimi danymi.
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "TrainingForm",
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
            reps:15,
            time:60,
            timeUnits:"min", //min, h, s
            series:1
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
                this.successCreateTraining = true
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
            const url = `${this.apiURL}sport/training/${this.trainingId}/add-exercise/${exerciseId}`
            const token = this.$store.getters.getToken;
            let time_seconds = 0;
            switch (this.timeUnits) {
                case "s":
                    time_seconds = this.time;
                    break;
                case "min":
                    time_seconds = this.time*60;
                    break;
                case "h":
                    time_seconds = this.time*3600;
                    break;
            }
            const data = {
                "reps":this.reps,
                "time_seconds":time_seconds,
                "series":3
            }
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
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
.register-btn {
    color: white;
    font-weight: bold;
}

.style-chooser .vs__search::placeholder,
.style-chooser .vs__dropdown-toggle,
.style-chooser .vs__dropdown-menu {
    background: #dfe5fb;
    border: none;
    color: #394066;
    text-transform: lowercase;
    font-variant: small-caps;
}

.style-chooser .vs__clear,
.style-chooser .vs__open-indicator {
    fill: #394066;
}
</style>
