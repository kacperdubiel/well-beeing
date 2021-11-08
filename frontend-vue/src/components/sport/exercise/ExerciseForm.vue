<template>
    <div class="modal fade" id="addExerciseModal" tabindex="-1" aria-labelledby="addExerciseModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="addExerciseModalLabel">Dodawanie nowego ćwiczenia</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs(); clearStatusWithTimeout()"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid" id="modal-container">
                        <div class="col-11 mx-auto form-group">
                            <div class="row">
                                <p class="form-label">Nazwa</p>
                                <input
                                    type="text"
                                    placeholder="Nazwa ćwiczenia"
                                    v-model="name"
                                    id="name-form"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateExercise && invalidName || nameTaken}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row text-end">
                                <p v-if="nameTaken" class="has-error m-0">
                                    Istnieje już ćwiczenie o takiej nazwie!
                                </p>
                            </div>
                            <div class="row ">
                                <div class="col-6 ps-0 mt-3">
                                    <p class="form-label mx-auto">Typ</p>
                                    <select
                                        v-model="type"
                                        class="float-start register-input p-2"
                                        :class="{ 'has-error': submittingCreateExercise && invalidType}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    >
                                        <option disabled value="">Wybierz typ</option>
                                        <option>OTHER</option>
                                        <option>STRENGTH</option>
                                        <option>CARDIO</option>
                                    </select>
                                </div>
                                <div class="col-6 ps-0 mt-3">
                                    <p class="form-label">MET</p>
                                    <input
                                        type="number"
                                        placeholder="MET"
                                        v-model="met"
                                        class="float-start register-input p-2"
                                        min=0.0
                                        max=30.0
                                        :class="{ 'has-error': submittingCreateExercise && invalidMet}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>

                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Etykiety</p>
                                <v-select
                                    class=" register-form style-chooser px-0 py-2"
                                    multiple v-model="values"
                                    :options=labelsSource
                                    :reduce="name => name.sportLabelId"
                                    label="name"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"/>
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Opis</p>
                                <textarea
                                    placeholder=" Opis"
                                    v-model="description"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateExercise && invalidDescription}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Instrukcja</p>
                                <textarea
                                    placeholder=" Instrukcja"
                                    v-model="instruction"
                                    class="register-input p-2"
                                    :class="{ 'has-error': submittingCreateExercise && invalidInstruction}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div v-if="errorCreateExercise" class="row text-end">
                                <p class="has-error m-0">
                                    Proszę uzupełnić wszystkie dane poprawnie!
                                </p>
                            </div>
                            <div class="row justify-content-end mt-3">
                                <div class="col-4">
                                    <button class="register-btn grey-btn p-2" @click="clearInputs()" data-bs-dismiss="modal">Anuluj</button>
                                </div>
                                <div class="col-4">
                                    <button class="register-btn btn-panel-sport p-2" @click="handleSubmit">Dodaj</button>
                                </div>
                            </div>
                        </div>

                        <div v-if="successCreateExercise" class="col-11 mx-auto submit-correct">
                            <p>
                                Dodano nowe ćwiczenie!
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import 'vue-select/dist/vue-select.css';
export default {
    name: "ExerciseForm",
    data () {
        return {
            values: [],
            name: "",
            type: "",
            met: 0.0,
            description: "",
            instruction: "",
            errorCreateExercise: false,
            successCreateExercise: false,
            nameTaken: false,
            submittingCreateExercise: false
        }
    },
    props: {
        labelsSource: Object,
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
            this.values = []
        },
        clearStatus() {
            this.errorCreateExercise = false
            this.successCreateExercise = false
            this.nameTaken = false
        },
        handleSubmit() {
            console.log('name: ' + this.name + ' type: ' + this.type + ' met: ' + this.met)
            this.submittingCreateExercise = true
            this.clearStatus()
            if (this.invalidName || this.invalidType || this.invalidMet || this.invalidDescription || this.invalidInstruction) {
                this.errorCreateExercise = true
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
                this.successCreateExercise = true
                this.clearInputs()
                this.$emit('get:exercises')
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateExercise = false
            document.getElementsByClassName('')
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

textarea.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

p.has-error {
    color: var(--INTENSE-PINK);
    /*font-weight: bold;*/
}
.submit-correct {
    color: var(--TELEMEDIC);
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
</style>
