<template>
    <div class="exercise" :id="exerciseSource.exerciseId">
        <div class="row mt-2">
            <div class="col-8">
                <p>{{exerciseSource.name}}</p>
            </div>
            <div class="col-4">
                <button class="btn-white">
                    <font-awesome-icon :icon="['fa', 'info']" />
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-12 additional">
                {{exerciseSource.exerciseType}} | {{exerciseSource.caloriesBurned}} kcal
            </div>

        </div>
    </div>
</template>

<script>
export default {
    name: "ExerciseNode",
    props: {
        exerciseSource: Object,
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
