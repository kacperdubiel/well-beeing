<template>
    <div id="addTrainingToPlanModal" aria-hidden="true" aria-labelledby="addTrainingToPlanModalLabel"
         class="modal fade "
         tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 id="addTrainingToPlanModalLabel" class="modal-title ms-2">Dodawanie treningu do planu</h5>
                    <button ref="closeModal" aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"
                            @click="clearStatus()"></button>
                </div>
                <div class="modal-body">
                    <div id="modal-container" class="container-fluid">
                        <div class="row mt-3 mx-4">

                            <div class="col-8 align-content-start text-start">
                                <p class="form-label">Data treningu :
                                    {{
                                        this.$store.getters.getTrainingPositionDate != null ? this.$func_global.dateDayMonth(this.$store.getters.getTrainingPositionDate) : ''
                                    }}</p>

                            </div>
                        </div>
                        <div class="row mt-3 mx-4 justify-content-start">
                            <div class="col-3 d-flex justify-content-start align-content-center">
                                <span class="form-label text-start float-start">Pora dnia:</span>
                            </div>
                            <div class="col-9 justify-content-start text-start float-start">
                                <select
                                    v-model="timeOfDay"
                                    :class="{ 'has-error': submittingAddTrainingToPlan && invalidTimeOfDay}"
                                    class="register-input p-2"
                                    style="border-radius: 5px"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                >
                                    <option disabled value="">Wybierz porę dnia</option>
                                    <option v-for="op in ['MORNING', 'NOON', 'AFTERNOON', 'EVENING', 'NIGHT']"
                                            :key="op" :value="op">{{ this.$func_global.mapTimeOfDay(op) }}
                                    </option>
                                </select>
                                <div v-if="invalidTimeOfDay && submittingAddTrainingToPlan" class="row text-end">
                                    <p class="has-error m-0">
                                        Wybierz porę dnia!
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-3 mx-4">
                            <div class="col-12 mx-auto form-group ">
                                <div class="row my-2 align-items-center justify-content-end">
                                    <span class="col-2 float-end" v-bind:class="{'active-view': !this.isListView}"
                                          @click="setListView(false)">
                                        <font-awesome-icon :icon="['fa', 'th']" class="icon"/>
                                    </span>
                                    <span class="col-2 float-end" v-bind:class="{'active-view': this.isListView}"
                                          @click="setListView(true)">
                                        <font-awesome-icon :icon="['fa', 'list-ul']" class="icon"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <TrainingsGridComponent v-if="!isListView" :mode="'toPlan'"
                                                :trainings-source="trainingsSource"/>
                        <TrainingsListComponent v-if="isListView" :mode="'toPlan'" :trainings-source="trainingsSource"/>
                        <div v-if="errorAddTrainingToPlan && !invalidTimeOfDay" class="row text-end">
                            <p class="has-error m-0">
                                Wybierz trening!
                            </p>
                        </div>
                        <div class="col-4">
                            <button class="register-btn btn-panel-sport p-2" @click="addToTrainingPlan">Dodaj</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingsGridComponent from "@/components/sport/training/TrainingsGridComponent";
import TrainingsListComponent from "@/components/sport/training/TrainingsListComponent";
import moment from "moment";

export default {
    name: "AddTrainingToPlanModal",
    components: {TrainingsListComponent, TrainingsGridComponent},
    data() {
        return {
            trainingId: -1,
            timeOfDay: '',
            date: '',
            submittingAddTrainingToPlan: false,
            successAddTrainingToPlan: false,
            errorAddTrainingToPlan: false,
            isListView: true,
        }
    },
    props: {
        trainingsSource: Array,
        trainingPlanId: Number
    },
    methods: {
        moment: function () {
            return moment();
        },
        setDate(newDate) {
            this.date = newDate
        },
        setListView(value) {
            console.log(value)
            this.isListView = value
        },
        addToTrainingPlan() {
            this.trainingId = this.$store.getters.getPlanTrainingId
            this.date = this.$store.getters.getTrainingPositionDate
            //http://localhost:8090/sport/training-plan/9/add-position
            this.submittingAddTrainingToPlan = true
            this.clearStatus()
            if (this.invalidTimeOfDay || this.date === '' || this.date == null || this.trainingId < 0) {
                this.errorAddTrainingToPlan = true
                return
            }
            const data = {
                trainingId: this.trainingId,
                date: this.date,
                trainingPlanId: this.trainingPlanId,
                timeOfDay: this.timeOfDay
            }


            const url = `${this.apiURL}sport/training-plan/${data.trainingPlanId}/add-position`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successAddTrainingToPlan = true
                this.$emit('new:plan')
                this.$refs.closeModal.click()
                // this.$emit('get:exercises')
            }).catch(error => {
                alert(error)
            });
            this.submittingAddTrainingToPlan = false
            document.getElementsByClassName('')
        },
        clearStatus() {
            this.errorAddTrainingToPlan = false
            this.successAddTrainingToPlan = false
            this.nameTaken = false
        },
    },
    mounted() {
        this.setDate(this.$store.getters.getTrainingPositionDate)
        this.$store.commit('setPlanTrainingId', -1)
    },
    computed: {
        invalidTimeOfDay() {
            return this.timeOfDay == null || this.timeOfDay === '';
        }
    }
}
Date.prototype.addDays = function (days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}
</script>

<style scoped>
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

.modal-body {
    background-color: var(--GREY2);
}

.form-label {
    color: white;
    font-weight: bold;
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

.register-btn {
    color: white;
    font-weight: bold;
}

.active-view {
    color: var(--SPORT);
}
</style>
