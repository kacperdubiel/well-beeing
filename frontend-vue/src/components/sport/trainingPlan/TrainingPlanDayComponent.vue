<template>
<div >
    <div class="m-2 day-name text-uppercase">
        {{day}}
    </div>
    <div class="m-2 text-uppercase">
        ({{date != null ? this.$func_global.dateDayMonth(date.date) : ""}})
    </div>
        <div v-for="position in positions" v-bind:key="position.trainingPositionId" class="row my-3">
            <TrainingInPlan :trainingPosition="position" @set:training="setTraining">

            </TrainingInPlan>
        </div>
    <div class="col " v-if="positions.length === 0 && !create">
        <p>Brak treningów</p>
    </div>

    <button v-if="create" @click="openAddTrainingToPlanModal(date.date)" data-bs-toggle="modal" data-bs-target="#addTrainingToPlanModal" >
        <font-awesome-icon class="icon  mx-4" :icon="['fa', 'plus-circle']"  />
    </button>
</div>
</template>

<script>
import TrainingInPlan from "@/components/sport/trainingPlan/TrainingInPlanComponent";
export default {
name: "TrainingPlanDay",
    components: {TrainingInPlan},
    data () {
        return {
            week:32,
            year:2021
        }
    },
    props: {
        day: String,
        positions: Array,
        create: Boolean,
        date: {} // {day:, date:}
    },
    methods: {
        openAddTrainingToPlanModal(date) {
            console.log(date)
            this.$store.commit('setTrainingPositionDate', date);
        },
        setTraining(training) {
            console.log('Dotarł 1', training)
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
