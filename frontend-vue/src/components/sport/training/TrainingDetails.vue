<template>
    <div>
        <div class="modal fade" id="infoTrainingModal" tabindex="-1" aria-labelledby="infoTrainingModalLabel" aria-hidden="true" >
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title ms-2" id="infoTrainingModalLabel">Szczegóły treningu</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs()"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid" id="modal-container">
                            <div class="col-11 mx-auto form-group" v-if="training != null">
                                <div class="row justify-content-between">
                                    <div class="col-4 px-0 ">
                                        <p class="form-label">Nazwa</p>
                                        <p class="info-value">{{training.name}}</p>
                                        <input
                                            v-if="this.edit"
                                            type="text"
                                            placeholder="Nazwa treningu"
                                            v-model="name"
                                            id="name-form"
                                            class="register-input p-2"
                                            :class="{ 'has-error': submittingEditTraining && invalidName || nameTaken}"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                        <p v-if="nameTaken && edit" class="has-error m-0">
                                            Istnieje już trening o takiej nazwie!
                                        </p>
                                    </div>
                                    <div class="col-4 px-0">
                                        <p class="form-label">Trudność</p>
                                        <p class="info-value">{{training.trainingDifficulty}}</p>
                                        <select
                                            v-model="trainingDifficulty"
                                            v-if="this.edit"
                                            class="register-input p-2"
                                            :class="{ 'has-error': submittingEditTraining && invalidDifficulty}"
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
                                        <p class="form-label">kcal</p>
                                        <p class="info-value">{{training.caloriesBurned}}</p>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <p class="form-label">Etykiety</p>
                                    <div class="container labels-container px-1 py-1" v-if="training.labels != null && training.labels.length > 0">
                                        <div class="form-label label-node p-2 mx-1 my-1"
                                             :style="{backgroundColor: randomColor(label.sportLabelId)}"
                                             v-for="label in training.labels" :key="label.sportLabelId">{{label.name}}</div>

                                    </div>
                                    <p v-else class="info-value">Brak etykiet</p>
                                </div>
                                <div class="row mt-3">
                                    <p class="form-label">Czas trwania</p>
                                    <p class="info-value">{{this.$func_global.getTimePrettyFromSeconds(training.totalTrainingTimeSeconds)}}</p>
                                </div>

                                <div class="row mt-3">
                                    <p class="form-label">Opis</p>
                                    <p class="info-value">{{training.description}}</p>
                                    <textarea
                                        placeholder=" Opis"
                                        v-if="this.edit"
                                        v-model="description"
                                        class="register-input p-2"
                                        :class="{ 'has-error': submittingEditTraining && invalidDescription}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <div class="row mt-3">
                                    <p class="form-label">Instrukcja</p>
                                    <p class="info-value">{{training.instruction}}</p>
                                    <textarea
                                        placeholder=" Instrukcja"
                                        v-if="this.edit"
                                        v-model="instruction"
                                        class="register-input p-2"
                                        :class="{ 'has-error': submittingEditTraining && invalidInstruction}"
                                        @focus="clearStatus"
                                        @keypress="clearStatus"
                                    />
                                </div>
                                <div class="row mt-3">
                                    <table class="table table-hover">
                                        <caption class="caption-top form-label">Cwiczenia w treningu</caption>
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
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr v-for="ex in this.training.exerciseInTrainings" :key="ex.exercise.exerciseId">
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
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div v-if="errorEditTraining" class="row text-end">
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
                            <div v-else>
                                NIE MA NIC
                            </div>

<!--                            <div v-if="successRegister" class="col-11 mx-auto">-->
<!--                                <p>-->
<!--                                    Rejestracja przebiegła pomyślnie!-->
<!--                                </p>-->
<!--                                <p>-->
<!--                                    Możesz zalogować się swoimi danymi.-->
<!--                                </p>-->
<!--                            </div>-->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "TrainingDetails",
    data () {
        return {
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
            }
        }
    },
    props: {
        trainingId: Number,
        training: Object
    },
    methods: {
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
            return this.name === ''
        },
        invalidType() {
            return this.type === ''
        },
        invalidDifficulty() {
            return this.trainingDifficulty === '';
        },
        invalidDescription() {
            return this.description === ''
        },
        invalidInstruction() {
            return this.instruction === ''
        }
    }
}
</script>

<style scoped>
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
