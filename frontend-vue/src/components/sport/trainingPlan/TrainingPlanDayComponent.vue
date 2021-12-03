<template>
    <div>
        <div class="m-2 day-name text-uppercase">
            {{ day }}
        </div>
        <div class="m-2 text-uppercase">
            ({{ date != null ? this.$func_global.dateDayMonth(date.date) : "" }})
        </div>
        <div v-for="position in positions" v-bind:key="position.trainingPositionId" class="row my-3">
            <TrainingInPlan :create="create" :details="details" :in-modal="inModal" :trainingPosition="position"
                            @update:active="updateActive" @set:training="setTraining">

            </TrainingInPlan>
        </div>
        <div v-if="positions.length === 0 && !create" class="col ">
            <p>Brak treningów</p>
        </div>

        <button v-if="create" data-bs-target="#addTrainingToPlanModal" data-bs-toggle="modal"
                @click="openAddTrainingToPlanModal(date.date)">
            <font-awesome-icon :icon="['fa', 'plus-circle']" class="icon  mx-4"/>
        </button>
    </div>
</template>

<script>
import TrainingInPlan from "@/components/sport/trainingPlan/TrainingInPlanComponent";

export default {
    name: "TrainingPlanDay",
    components: {TrainingInPlan},
    data() {
        return {
            week: 32,
            year: 2021
        }
    },
    props: {
        day: String,
        positions: Array,
        create: Boolean,
        details: Boolean,
        inModal: Boolean,
        date: {} // {day:, date:}
    },
    methods: {
        updateActive() {
            this.$emit('update:active')
        },
        openAddTrainingToPlanModal(date) {
            console.log(date)
            this.$store.commit('setTrainingPositionDate', date);
        },
        setTraining(training) {
            this.$emit('set:training', training)
        }
    }
}
</script>

<style scoped>
.day-name {
    font-size: 20px;
    font-weight: bold;
}

.icon {
    font-size: 40px;
}
</style>
