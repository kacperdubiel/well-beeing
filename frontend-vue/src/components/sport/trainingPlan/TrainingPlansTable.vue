<template>
    <div class="container my-3">
        <div class="row justify-content-center">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Tydzień</th>
                        <th scope="col">Liczba treningów</th>
                        <th scope="col">Status</th>
                        <th scope="col">Kalorie do spalenia</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="plan in trainingPlansSource" :key="plan.trainingPlanId">
                    <td>{{ plan.trainingPlanId }}</td>
                    <td>{{
                            this.$func_global.getWeekRangeFromMonday(new Date(plan.beginningDate))
                        }}</td>
                    <td>{{plan.trainingPositions.length}}</td>
                    <td>{{ plan.planStatus }}</td>
                    <td>{{ plan.caloriesBurned }}</td>
                    <td>
                        <button class="btn-white mx-2" @click="openInfoModal(plan)" data-bs-toggle="modal" href="#infoPlanModal">
                            <font-awesome-icon :icon="['fa', 'info']" />
                        </button>
                        <button class="btn-white" @click="downloadPlan(plan.trainingPlanId)">
                            <font-awesome-icon :icon="['fa', 'download']" />
                        </button>
                        <button class="btn-white mx-2" v-if="enableButtons">
                            <font-awesome-icon :icon="['fa', 'trash']" />
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <TrainingPlanDetails :trainingPlan="trainingPlan" @download:plan="downloadPlan"/>
    </div>
</template>

<script>

import moment from "moment";

import TrainingPlanDetails from "@/components/sport/trainingPlan/TrainingPlanDetails";
export default {
    name: "TrainingPlansTable",
    components: {TrainingPlanDetails},
    data() {
        return {
            trainingPlan: null,
            enableButtons: false
        }
    },
    props: {
        trainingPlansSource: Object
    },
    methods: {
        moment() {
            return moment()
        },
        downloadPlan(planId) {
            this.$emit('download:plan',planId)
        },
        openInfoModal(plan) {
            this.trainingPlan = plan;
            this.$emit('update:items')
        }
    }
}
</script>

<style scoped>
th, td {
    color: var(--bs-white);
}
table {
    --bs-table-hover-color: none;
    color: white;
    border-bottom: none;
}
</style>
