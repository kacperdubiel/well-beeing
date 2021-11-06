<template>
    <div>
        <div class="modal fade" id="infoPlanModal" tabindex="-1" aria-labelledby="infoPlanModalLabel" aria-hidden="true" >
            <div class="modal-dialog modal-xl modal-dialog-centered">
                <div class="modal-content" v-if="trainingPlan != null">
                    <div class="modal-header">
                        <h5 class="modal-title ms-2" id="infoPlanModalLabel">Szczegóły planu na tydzień {{this.$func_global.getDateRangeOfWeek(trainingPlan.week)}}</h5>
                        <button class="btn-white" @click="downloadPlan(trainingPlan.trainingPlanId)">
                            <font-awesome-icon :icon="['fa', 'download']" />
                        </button>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid" id="modal-container">
                            <div class="col-11 mx-auto form-group">
                                <div class="row justify-content-between">
                                    <training-plan-week @update:active="getMyTrainingPlans" :plan="trainingPlan" :week-dates="this.$func_global.getDatesArrayOfWeek(trainingPlan.week)" :plan-type="'details'" :days="this.$func_global.days"/>
                                </div>
                                <div class="row mt-3">
<!--                                    <table class="table table-hover">-->
<!--                                        <caption class="caption-top form-label">Cwiczenia w treningu</caption>-->
<!--                                        <thead>-->
<!--                                        <tr>-->
<!--                                            <th scope="col">Id</th>-->
<!--                                            <th scope="col">Nazwa</th>-->
<!--                                            <th scope="col">Typ</th>-->
<!--                                            <th scope="col">kcal</th>-->
<!--                                            <th scope="col">Powt</th>-->
<!--                                            <th scope="col">Serie</th>-->
<!--                                            <th scope="col">Czas</th>-->
<!--                                            <th scope="col"></th>-->
<!--                                        </tr>-->
<!--                                        </thead>-->
<!--                                        <tbody>-->
<!--                                        <tr v-for="ex in this.training.exerciseInTrainings" :key="ex.exercise.exerciseId">-->
<!--                                            <td>{{ ex.exercise.exerciseId }}</td>-->
<!--                                            <td>{{ ex.exercise.name }}</td>-->
<!--                                            <td>{{ ex.exercise.exerciseType }}</td>-->
<!--                                            <td>{{ ex.caloriesBurned }}</td>-->
<!--                                            <td>{{ ex.repetitions }}</td>-->
<!--                                            <td>{{ ex.series }}</td>-->
<!--                                            <td>{{ ex.time_seconds }}</td>-->
<!--                                            <td>-->
<!--                                                <button class="btn-white mx-2" @click="openInfoModal(ex.exercise.exerciseId)" data-bs-toggle="modal" data-bs-target="#infoExerciseModal">-->
<!--                                                    <font-awesome-icon :icon="['fa', 'info']" />-->
<!--                                                </button>-->
<!--                                            </td>-->
<!--                                        </tr>-->
<!--                                        </tbody>-->
<!--                                    </table>-->
                                </div>
                                <div v-if="errorEditTrainingPlan" class="row text-end">
                                    <p class="has-error m-0">
                                        Proszę uzupełnić wszystkie dane poprawnie!
                                    </p>
                                </div></div>
                        </div>
                    </div>
                </div>
                <div v-else>
                    NIE MA NIC
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingPlanWeek from "@/components/sport/trainingPlan/TrainingPlanWeek";
export default {
    name: "TrainingPlanDetails",
    components: {TrainingPlanWeek},
    data() {
        return {
            errorEditTrainingPlan:false,
            edit: false
        }
    },
    props: {
        trainingPlan: Object
    },
    methods: {
        downloadPlan(planId) {
            this.$emit('download:plan', planId);
        },
        getMyTrainingPlans() {
            this.$emit('update:active');
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
</style>
