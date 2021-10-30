<template>
    <div class="exercise" :id="trainingSource.exerciseId">
        <div class="row mt-2">
            <div class="col-8">
                <p>{{trainingSource.name}}</p>
            </div>
            <div class="col-4">
                <button class="btn-white">
                    <font-awesome-icon :icon="['fa', 'info']" />
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-12 additional">
                {{trainingSource.trainingDifficulty}}
            </div>

        </div>
        <div class="row">
            <div class="col-12 additional">
                {{trainingSource.time}} | {{trainingSource.caloriesBurned}} kcal
            </div>

        </div>
    </div>
</template>

<script>
export default {
    name: "TrainingNode",
    props: {
        trainingSource: Object,
    },
}
</script>

<style scoped>
.row.mt-2 {
    height: 50px;
}
.additional {
    font-size: 12px;
}
</style>
