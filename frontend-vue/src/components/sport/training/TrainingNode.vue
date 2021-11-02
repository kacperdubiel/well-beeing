<template>
    <div
         :id="trainingSource.trainingId"
    >
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
        mode: String
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
.btn-white {
    font-size: x-small;
}
</style>
