<template>
<div >
    <div class="m-2 day-name text-uppercase">
        {{day}}
    </div>
        <div v-for="position in positions" v-bind:key="position.trainingPositionId" class="row my-3">
            <TrainingInPlan :trainingPosition="position">

            </TrainingInPlan>
        </div>
    <div class="col " v-if="positions.length === 0">
            <p>Brak treningów</p>
    </div>
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
        positions: Array
    }
}
</script>

<style scoped>
.day-name {
    font-size: 20px;
    font-weight: bold;
}
</style>
