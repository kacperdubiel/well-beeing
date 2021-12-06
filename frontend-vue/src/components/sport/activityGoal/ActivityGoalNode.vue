<template>
    <div v-if="activityGoalSource" class="container activity-goal-node section-bg fw-bold p-3">
        <div class="row justify-content-start">
            <div class="col">
                <p class="text-start">{{ this.$func_global.mapActivityGoal(activityGoalSource.goalType) }}
                    {{ activityGoalSource.numericValue }} kg/tydz.</p>
            </div>
        </div>
        <div class="row justify-content-start">
            <div class="col">
                Od {{ this.$func_global.formatDate(activityGoalSource.goalCreationDate) }} Do
                {{ this.$func_global.formatDate(activityGoalSource.goalTargetDate) }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ActivityGoalNode",
    props: {
        activityGoalSource: Object
    }
}
</script>

<style scoped>
.activity-goal-node.section-bg {
    min-height: 0;
    background-color: var(--SPORT);
}
</style>
