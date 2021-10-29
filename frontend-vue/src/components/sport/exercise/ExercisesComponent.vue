<template>
<div>
    <div class="add-exercise row my-2 align-items-center">
        <span class="h3 col-8 offset-2 text-end">Dodaj</span>
        <span class="col-2 float-end">
            <font-awesome-icon class="icon  mx-4" :icon="['fa', 'plus-circle']" data-bs-toggle="modal" data-bs-target="#addExerciseModal" />
        </span>
    </div>
    <ExercisesListComponent :exercises-source="exercises"/>
    <ExercisesGridComponent :exercises-source="exercises"/>
    <!--Modal-->
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
                            <div class="row mt-3">
                                <p class="form-label">Typ</p>
                                <select
                                    v-model="type"
                                    class="register-input p-2"
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
                            <div class="row mt-3">
                                <p class="form-label">MET</p>
                                <input
                                    type="number"
                                    placeholder="MET"
                                    v-model="met"
                                    class="register-input p-2"
                                    min=0.0
                                    max=30.0
                                    :class="{ 'has-error': submittingCreateExercise && invalidMet}"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                />
                            </div>
                            <div class="row mt-3">
                                <p class="form-label">Etykiety</p>
                                <v-select multiple v-model="values" :options=labels :reduce="name => name.sportLabelId" label="name"/>
                                <p class="form-label">{{ values }}</p>
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
                                    <button class="register-btn grey-btn p-2" @click="clearInputs(); clearStatusWithTimeout()" data-bs-dismiss="modal">Anuluj</button>
                                </div>
                                <div class="col-4">
                                    <button class="register-btn btn-panel-sport p-2" @click="addExercise">Dodaj</button>
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
</div>
</template>

<script>
import ExercisesListComponent from "@/components/sport/exercise/ExercisesListComponent";
import ExercisesGridComponent from "@/components/sport/exercise/ExercisesGridComponent";
export default {
    name: "ExercisesComponent",
    components: {
        ExercisesGridComponent,
        ExercisesListComponent,
    },
    data () {
        return {
            values: [],
            options: ['Select option', 'options', 'selected', 'mulitple', 'label', 'searchable', 'clearOnSelect', 'hideSelected', 'maxHeight', 'allowEmpty', 'showLabels', 'onChange', 'touched'],
            exercises: [],
            labels: [],
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
    methods: {
        async getExercises () {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercises = response.data['objects']
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
                this.labels = response.data
                console.log(this.labels)
            }).catch(error => {
                console.log(error.response);
            });
        },
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
            this.errorCreateExercise = false
            this.successCreateExercise = false
            this.nameTaken = false
        },
        addExercise() {
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
                this.getExercises()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.nameTaken = true
                }
            });
            this.submittingCreateExercise = false
            document.getElementsByClassName('')
        },
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
        this.getExercises();
        this.getLabels();
    }

}
</script>

<style scoped>
.icon {
    font-size: 30px;
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
.register-btn {
    color: white;
    font-weight: bold;
}
</style>
