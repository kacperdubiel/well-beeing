<template>
<div >
    <div class="m-2 day-name text-uppercase">
        {{day}}
    </div>
        <div v-for="numa in [1,2,3]" v-bind:key="numa" class="row my-3">
            <TrainingInPlan>

            </TrainingInPlan>
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
        day: String
    }
}
</script>

<style scoped>
.day-name {
    font-size: 20px;
    font-weight: bold;
}
</style>
