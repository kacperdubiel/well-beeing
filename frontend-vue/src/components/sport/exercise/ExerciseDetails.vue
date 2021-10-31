<template>
<div>
    <div class="modal fade" id="infoExerciseModal" tabindex="-1" aria-labelledby="infoExerciseModalLabel" aria-hidden="true" >
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="infoExerciseModalLabel">Szczegóły ćwiczenia</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs(); clearStatusWithTimeout()"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid" id="modal-container">
                        <div class="col-11 mx-auto form-group">
                            <div class="row justify-content-between">
                                <div class="col-4 px-0 ">
                                    <p class="form-label">Nazwa</p>
                                    <p class="info-value">{{exercise.name}}</p>
                                    <input
                                        v-if="this.edit"
                                        type="text"
                                        placeholder="Nazwa ćwiczenia"
                                        v-model="name"
                                        id="name-form"
                                        class="register-input p-2"
                                        :class="{ 'has-error': submittingEditExercise && invalidName || nameTaken}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                    <p v-if="nameTaken && edit" class="has-error m-0">
                                        Istnieje już ćwiczenie o takiej nazwie!
                                    </p>
                                </div>
                                <div class="col-4 px-0">
                                    <p class="form-label">Typ</p>
                                    <p class="info-value">{{exercise.exerciseType}}</p>
                                    <select
                                        v-model="type"
                                        v-if="this.edit"
                                        class="register-input p-2"
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
                                <div class="col-4 px-0">
                                    <p class="form-label">kcal/h</p>
                                    <p class="info-value">{{exercise.caloriesBurned}}</p>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Etykiety</p>
                                <div class="container labels-container px-1 py-1">
                                    <div class="form-label label-node p-2 mx-1 my-1"
                                       :style="{backgroundColor: randomColor()}"
                                        v-for="label in exercise.labels" :key="label.sportLabelId">{{label.name}}</div>

                                </div>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Wydatek energetyczny (intensywność)</p>
                                <p class="info-value">{{exercise.met}} METs</p>
                                <input
                                    type="number"
                                    placeholder="MET"
                                    v-model="met"
                                    v-if="this.edit"
                                    class="register-input p-2"
                                    min=0.0
                                    max=30.0
                                    :class="{ 'has-error': submittingEditExercise && invalidMet}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>

                            <div class="row mt-3">
                                <p class="form-label">Opis</p>
                                <p class="info-value">{{exercise.description}}</p>
                                <textarea
                                    placeholder=" Opis"
                                    v-if="this.edit"
                                    v-model="description"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingEditExercise && invalidDescription}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Instrukcja</p>
                                <p class="info-value">{{exercise.instruction}}</p>
                                <textarea
                                    placeholder=" Instrukcja"
                                    v-if="this.edit"
                                    v-model="instruction"
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
<!--                            <div class="row justify-content-end mt-3">-->
<!--                                <div class="col-4">-->
<!--                                    <button class="register-btn grey-btn p-2" @click="clearInputs(); clearStatusWithTimeout()" data-bs-dismiss="modal">Anuluj</button>-->
<!--                                </div>-->
<!--                                <div class="col-4">-->
<!--                                    <button class="register-btn btn-panel-sport p-2" @click="handleSubmit">Dodaj</button>-->
<!--                                </div>-->
<!--                            </div>-->
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
</div>
</template>

<script>
export default {
    name: "ExerciseDetails",
    data () {
        return {
            edit: false,
            exercise: Object,
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
            }
        }
    },
    props: {
        exerciseId: Number
    },
    methods: {
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
        clearInputs() {
            this.name = ""
            this.type = ""
            this.met = 0.0
            this.description = ""
            this.instruction = ""
            this.values = []
        },
        clearStatus() {
            this.errorEditExercise = false
            this.successEditExercise = false
            this.nameTaken = false
        },
        randomColor() {
            const r = () => Math.floor(256 * Math.random());

            return `rgb(${r()}, ${r()}, ${r()})`;
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
</style>
