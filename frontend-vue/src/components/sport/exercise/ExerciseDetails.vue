<template>
<div>
    <div class="modal fade" id="infoExerciseModal" tabindex="-1" aria-labelledby="infoExerciseModalLabel" aria-hidden="true" >
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="infoExerciseModalLabel">Szczegóły ćwiczenia {{edit ? '- tryb edycji' : ''}}</h5>
                    <button class="btn-white" @click="editExercise()">
                        <font-awesome-icon :icon="['fa', 'pencil-alt']" />
                    </button>
                    <button type="button" class="btn-close" ref="closeModal" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs()"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid" id="modal-container"  v-if="exercise != null">
                        <div class="col-11 mx-auto form-group">
                            <div class="row justify-content-between">
                                <div class="col-5 px-0 justify-content-start">
                                    <p class="form-label">{{ edit ? 'Poprzednia nazwa' : 'Nazwa' }}</p>
                                    <p class="info-value">{{exercise.name}}</p>
                                </div>
                                <div class="col-4 px-0">
                                    <p class="form-label">{{ edit ? 'Poprzedni typ' : 'Typ' }}</p>
                                    <p class="info-value">{{exercise.exerciseType}}</p>
                                </div>
                                <div class="col-3 px-0">
                                    <p class="form-label">kcal/h</p>
                                    <p class="info-value">{{exercise.caloriesBurned}}</p>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">{{ edit ? 'Poprzednie etykiety' : 'Etykiety' }}</p>
                                <div class="container labels-container px-1 py-1" v-if="exercise.labels != null && exercise.labels.length > 0">
                                    <div class="form-label label-node p-2 mx-1 my-1"
                                       :style="{backgroundColor: randomColor(label.sportLabelId)}"
                                        v-for="label in exercise.labels" :key="label.sportLabelId">{{label.name}}</div>

                                </div>
                                <p v-else class="info-value">Brak etykiet</p>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">{{ edit ? 'Poprzedni wydatek energetyczny (intensywność)' : 'Wydatek energetyczny (intensywność)' }}</p>
                                <p class="info-value">{{exercise.met}} METs</p>
                            </div>

                            <div class="row mt-3">
                                <p class="form-label">{{ edit ? 'Poprzedni opis' : 'Opis' }}</p>
                                <p class="info-value">{{exercise.description}}</p>
                            </div>
                            <div class="row mt-3 ">
                                <p class="form-label">{{ edit ? 'Poprzednia instrukcja' : 'Instrukcja' }}</p>
                                <p class="info-value" v-html="exercise.instruction"></p>
                            </div>
                            <div class="row mt-3 " v-if="this.edit">
                                <h5 class="modal-title mt-3 text-start ps-0">Edytowane ćwiczenie</h5>
                            </div>
                            <div class="row justify-content-between">
                                <div class="col-5 px-0 justify-content-start">
                                    <p v-if="this.edit" class="form-label">Nowa nazwa:</p>
                                    <input
                                        v-if="this.edit"
                                        type="text"
                                        placeholder="Nazwa ćwiczenia"
                                        v-model="editedExercise.name"
                                        id="name-form"
                                        class="register-input p-2 float-start me-auto"
                                        :class="{ 'has-error': submittingEditExercise && invalidName || nameTaken}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                    <p v-if="nameTaken && edit" class="has-error m-0">
                                        Istnieje już ćwiczenie o takiej nazwie!
                                    </p>
                                </div>
                                <div class="col-4 px-0">
                                    <p v-if="this.edit" class="form-label">Nowy typ:</p>
                                    <select
                                        v-if="this.edit"
                                        v-model="editedExercise.exerciseType"
                                        class="register-input p-2 float-start me-auto"
                                        :class="{ 'has-error': submittingEditExercise && invalidType}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    >
                                        <option disabled value="">Wybierz typ</option>
                                        <option>OTHER</option>
                                        <option>STRENGTH</option>
                                        <option>CARDIO</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <p v-if="this.edit" class="form-label mt-2">Nowe etykiety:</p>
                                <v-select
                                    v-if="edit"
                                    class=" register-form style-chooser px-0"
                                    multiple v-model="values"
                                    :options=labelsSource
                                    :reduce="name => name.sportLabelId"
                                    label="name"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"/>
                            </div>
                            <div class="row mt-3">
                                <p v-if="this.edit" class="form-label">Nowy wydatek energetyczny (intensywność):</p>
                                <input
                                    type="number"
                                    placeholder="MET"
                                    v-model="editedExercise.met"
                                    v-if="this.edit"
                                    class="register-input w-auto p-2"
                                    min=0.0
                                    max=30.0
                                    :class="{ 'has-error': submittingEditExercise && invalidMet}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>

                            <div class="row mt-3">
                                <p v-if="this.edit" class="form-label">Nowy opis:</p>
                                <textarea
                                    placeholder=" Opis"
                                    v-if="this.edit"
                                    v-model="editedExercise.description"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingEditExercise && invalidDescription}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row mt-3 ">
                                <p v-if="this.edit" class="form-label">Nowa instrukcja:</p>
                                <textarea
                                    placeholder=" Instrukcja"
                                    v-if="this.edit"
                                    v-model="editedExercise.instruction"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingEditExercise && invalidInstruction}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div v-if="errorEditExercise" class="row text-end">
                                <p class="has-error m-0">
                                    Proszę uzupełnić wszystkie dane poprawnie!
                                </p>
                            </div>
                            <div class="row mt-3 justify-content-between" v-if="edit">
                                <div class="col-4 ">
                                    <button class="grey-btn h-100 text-white float-start" @click="cancelEditExercise()">
                                        Anuluj edycję
                                    </button>
                                </div>
                                <div class="col-4">
                                    <button class="btn-panel-sport" @click="submitEditExercise()">
                                        Zapisz zmiany
                                    </button>
                                </div>
                            </div>

<!--                            <div class="row justify-content-end mt-3">-->
<!--                                <div class="col-4">-->
<!--                                    <button class="register-btn grey-btn p-2" @click="clearInputs(); clearStatusWithTimeout()" data-bs-dismiss="modal">Anuluj</button>-->
<!--                                </div>-->
<!--                                <div class="col-4">-->
<!--                                    <button class="register-btn btn-panel-sport p-2" @click="handleSubmit">Dodaj</button>-->
<!--                                </div>-->
<!--                            </div>-->
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
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: "ExerciseDetails",
    data () {
        return {
            edit: false,
            values: [],
            name: "",
            type: "",
            met: 0.0,
            description: "",
            instruction: "",
            errorEditExercise: false,
            successEditExercise: false,
            nameTaken: false,
            submittingEditExercise: false,
            labelStyle: {
                backgroundColor:this.randomColor()
            },
            editedExercise: Object,
            labelsSource: Array
        }
    },
    props: {
        exerciseId: Number,
        exercise: Object
    },
    methods: {
        cancelEditExercise() {
          this.editedExercise = {}
          this.values = []
          this.edit = false
        },
        editExercise(){
            this.editedExercise = Object.assign({},this.exercise)
            this.values = this.editedExercise.labels.map(l => l.sportLabelId)

            console.log('Array Labels ',this.values)
            this.edit = true
        },
        async submitEditExercise() {
            this.$emit('submit:editExercise', this.editedExercise, this.values)
            this.edit = false
            this.$refs.closeModal.click()
        },
        async getExercise () {
            const url = `${this.apiURL}sport/exercise/${this.$store.getters.getExerciseId}`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercise = response.data
                console.log(this.exercises)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getLabels () {
            const url = `${this.apiURL}sport/exercise/labels`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.labelsSource = response.data
                console.log(this.labelsSource)
            }).catch(error => {
                console.log(error.response);
            });
        },
        clearInputs() {
            this.name = ""
            this.type = ""
            this.met = 0.0
            this.description = ""
            this.instruction = ""
            // this.values = []
        },
        clearStatus() {
            this.errorEditExercise = false
            this.successEditExercise = false
            this.nameTaken = false
        },
        randomColor(seed) {
            let availableColors = ['#C33149', '#FEA12A', '#08415C', '#0E9594', '#8FB339', '#90E39A', '#96E6B3', '#5386E4', '#585123', '#802392']
            return availableColors[seed % availableColors.length];
        }
    },
    computed: {
        invalidName() {
            return this.name === ''
        },
        invalidType() {
            return this.type === ''
        },
        invalidMet() {
            return this.met <= 0.0 || this.met >= 30.0;
        },
        invalidDescription() {
            return this.description === ''
        },
        invalidInstruction() {
            return this.instruction === ''
        }
    },
    mounted() {
        this.getLabels()
    }
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
.info-value {
    color: var(--GREY3);
    text-align: left;
    padding-left: 0;
}
.register-btn {
    color: white;
    font-weight: bold;
}
.modal-dialog {
    max-width: 800px;
    width: 40%;
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
.register-input {
    box-sizing:border-box;
}
</style>
