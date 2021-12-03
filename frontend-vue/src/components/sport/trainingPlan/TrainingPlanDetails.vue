<template>
    <div>
        <div id="infoPlanModal" aria-hidden="true" aria-labelledby="infoPlanModalLabel" class="modal fade"
             tabindex="-1">
            <div class="modal-dialog modal-xl modal-dialog-centered">
                <div v-if="trainingPlan != null" class="modal-content">
                    <div class="modal-header">
                        <h5 id="infoPlanModalLabel" class="modal-title ms-2">Szczegóły planu na tydzień
                            {{ this.$func_global.getWeekRangeFromMonday(new Date(trainingPlan.beginningDate)) }}</h5>
                        <button class="btn-white" @click="downloadPlan(trainingPlan.trainingPlanId)">
                            <font-awesome-icon :icon="['fa', 'download']"/>
                        </button>
                        <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"></button>
                    </div>
                    <div class="modal-body">
                        <div id="modal-container" class="container-fluid">
                            <div class="col-11 mx-auto form-group">
                                <div class="row justify-content-between">
                                    <training-plan-week :days="this.$func_global.days"
                                                        :plan="trainingPlan"
                                                        :plan-type="'modal'"
                                                        :week-dates="this.$func_global.getDatesArrayFromMonday(new Date(trainingPlan.beginningDate))" @update:active="getMyTrainingPlans"/>
                                </div>
                                <div class="row mt-3">
                                </div>
                                <div v-if="errorEditTrainingPlan" class="row text-end">
                                    <p class="has-error m-0">
                                        Proszę uzupełnić wszystkie dane poprawnie!
                                    </p>
                                </div>
                            </div>
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
            errorEditTrainingPlan: false,
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
