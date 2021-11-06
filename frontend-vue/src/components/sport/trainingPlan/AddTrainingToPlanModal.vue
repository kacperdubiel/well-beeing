<template>
    <div class="modal fade" id="addTrainingToPlanModal" tabindex="-1" aria-labelledby="addTrainingToPlanModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title ms-2" id="addTrainingToPlanModalLabel">Dodawanie treningu do planu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs()"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid" id="modal-container">
                        <div class="row mt-3 mx-4">

                            <div class="col-8 align-content-start text-start">
                                <p class="form-label">Data treningu : {{this.$store.getters.getTrainingPositionDate != null ? this.$func_global.dateDayMonth(this.$store.getters.getTrainingPositionDate) : ''}}</p>

                            </div>
                        </div>
                        <div class="row mt-3 mx-4 justify-content-start">
                            <div class="col-3 d-flex justify-content-start align-content-center">
                                <span class="form-label text-start float-start">Pora dnia:</span>
                            </div>
                            <div class="col-9 justify-content-start text-start float-start">

                                <!--                                <v-select-->
                                <!--                                    class="style-chooser pb-2"-->
                                <!--                                    v-model="partOfDay"-->
                                <!--                                    :options="['RANO', 'POLUDNIE', 'WIECZÓR']"-->
                                <!--                                />-->
                                <select
                                    v-model="timeOfDay"
                                    class="register-input p-2"
                                    style="border-radius: 5px"
                                    @focus="clearStatus"
                                    @keypress="clearStatus"
                                >
                                    <option disabled value="">Wybierz porę dnia</option>
                                    <option v-for="op in ['MORNING', 'NOON', 'AFTERNOON', 'EVENING', 'NIGHT']" :key="op">{{ op }}</option>
                                </select>
                                <p class="form-label">{{ timeOfDay }}</p>
                            </div>
                        </div>

                        <div class="row mt-3 mx-4">
                            <div class="col-12 mx-auto form-group ">
                                <div class="row my-2 align-items-center justify-content-end">
                                    <span class="col-2 float-end" v-bind:class="{'active-view': !this.isListView}" @click="setListView(false)">
                                        <font-awesome-icon  class="icon" :icon="['fa', 'th']" />
                                    </span>
                                                            <span class="col-2 float-end" v-bind:class="{'active-view': this.isListView}" @click="setListView(true)">
                                        <font-awesome-icon  class="icon" :icon="['fa', 'list-ul']" />
                                    </span>
                                </div>
                            </div>
                        </div>
                        <TrainingsGridComponent v-if="!isListView" :mode="'toPlan'" :trainings-source="trainingsSource"/>
                        <TrainingsListComponent v-if="isListView" :mode="'toPlan'" :trainings-source="trainingsSource"/>
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
          isListView: true
      }
    },
    props: {
        trainingsSource: Array,
        trainingPlanId: Number
    },
    methods: {
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
            if (this.timeOfDay === '' || this.date === '' || this.date == null) {
                this.errorCreateExercise = true
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
    }
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
