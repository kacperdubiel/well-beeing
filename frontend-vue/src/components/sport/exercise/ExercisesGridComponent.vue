<template>
    <div class="container my-3">

        <div class="row justify-content-center">
            <div class="col-12">
<!--                <div class="row mb-3 px-3 mt-3 mw-100">-->
<!--                    <div class="col-md-6 search-info">-->
<!--                        <span id="search-results">Wyniki wyszukiwania dla frazy: </span>-->
<!--&lt;!&ndash;                        <span class="fst-italic">"{{this.activeSearchWord}}"</span>&ndash;&gt;-->
<!--                        <button class="btn btn-sm btn-outline-4 size" type="button" id="button-cancel">-->
<!--                            <font-awesome-icon :icon="['fa', 'info']" />-->
<!--                        </button>-->
<!--                    </div>-->
<!--                </div>-->
                <div class="row justify-content-center mw-100 mt-3">
                    <div class="col-lg-12 col-md-9 col-sm-7 col-10">
                        <div class="row align-content-center">
                            <div class="col-xl-3 col-lg-4 col-md-12 col-sm-12 col-12 px-2 py-2 mx-0 section-bg training" v-for="ex in exercisesSource" :key="ex.exerciseId"
                                 v-bind:class="{'selected-exercise' : (mode === 'toTraining' && this.$store.getters.getExerciseToTrainingId === ex.exerciseId) }"
                                 v-on:click="mode === 'toTraining' ? this.$store.commit('setExerciseToTrainingId', ex.exerciseId) : null">
                                <exercise-node :exercise-source="ex" @set:exercise="setExercise"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <ExerciseDetails :exercise="exercise"/>
    </div>
</template>

<script>
import ExerciseNode from "@/components/sport/exercise/ExerciseNode";
import ExerciseDetails from "@/components/sport/exercise/ExerciseDetails";
export default {
    name: "ExercisesGridComponent",
    components: {ExerciseDetails, ExerciseNode},
    data () {
        return {
            exercises: [this.exercise],
            exercise: {}
        }

    },
    props: {
        exercisesSource: Array,
        mode: String
    },
    methods: {
        setExercise(exercise) {
            this.exercise = exercise
        }
    }
}
</script>

<style scoped>

.training{
    font-weight: bold;
    text-align: left;
    border-radius: 2px;
    background-color: var(--GREY3);
}
.selected-exercise {
    background-color: var(--SPORT);
}
</style>
