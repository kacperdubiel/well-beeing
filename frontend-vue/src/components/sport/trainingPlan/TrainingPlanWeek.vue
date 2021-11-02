<template>
    <div class="container align-items-start" style="overflow: auto" v-if="plan.trainingPlanId != null">
        <div class="row-fluid d-flex align-items-start">
            <div v-for="day in days" :key="day.num" class="col-4 day mx-1  mb-3 p-3">
                <TrainingPlanDay :date="weekDates.find(d => d.day.num === day.num)" :day=day.name :create="planType === 'create'" :positions="plan.trainingPositions.filter( pos => matchesDayOfTheWeek(pos, day.num))">

                </TrainingPlanDay>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingPlanDay from "@/components/sport/trainingPlan/TrainingPlanDayComponent";

export default {
    name: "TrainingPlanWeek",
    components: { TrainingPlanDay},
    props: {
        plan: {
            trainingPositions: []
        },
        planType: String, // active, create, other, editable,
        days: [],
        weekDates: []
    },
    methods: {
    matchesDayOfTheWeek(position, day) {
        return new Date(position.trainingDate).getDay() === day;
    }
    }
}
</script>

<style scoped>
.row-fluid{
    white-space: nowrap;
}
.row-fluid .col-4{
    display: inline-block;
}
.col-4 { white-space: normal; }
.day {
    background-color: var(--GREY3);
    border-radius: 5px;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}
</style>
