<template>
    <div class="exercise" :id="exerciseSource.exerciseId">
        <div class="row mt-2">
            <div class="col-8">
                <p>{{exerciseSource.name}}</p>
            </div>
            <div class="col-4">
                <button class="btn-white mx-2" @click="openInfoModal(exerciseSource)" data-bs-toggle="modal" data-bs-target="#infoExerciseModal">
                    <font-awesome-icon :icon="['fa', 'info']" />
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-12 additional">
                {{this.$func_global.mapExerciseType(exerciseSource.exerciseType)}} | {{exerciseSource.caloriesBurned}} kcal
            </div>

        </div>
    </div>
</template>

<script>

export default {
    name: "ExerciseNode",
    props: {
        exerciseSource: Object,
        mode: String,
    },
    methods: {
        openInfoModal(exercise) {
            this.$emit('set:exercise', exercise)
        }
    }
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
