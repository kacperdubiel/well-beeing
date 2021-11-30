/* eslint-disable */
<template>
    <div class="container my-3">
        <div class="row justify-content-center">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Typ</th>
                    <th scope="col">kcal/h</th>
                    <th v-if="!inModal" scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="ex in exercisesSource" :key="ex.name"
                    v-bind:class="{'selected-exercise' : (mode === 'toTraining' && this.$store.getters.getExerciseToTrainingId === ex.exerciseId) }"
                    v-on:click="mode === 'toTraining' ? this.$store.commit('setExerciseToTrainingId', ex.exerciseId) : null">
                    <td>{{ ex.exerciseId }}</td>
                    <td>{{ ex.name }}</td>
                    <td>{{ this.$func_global.mapExerciseType(ex.exerciseType) }}</td>
                    <td>{{ ex.caloriesBurned }}</td>
                    <td v-if="!inModal" class="text-end">
                        <button
                            v-if="this.$store.getters.getRoles.includes('ROLE_TRAINER') && this.$store.getters.getProfileId === ex.creatorId"
                            class="btn-white mx-2"
                            @click="deleteExercise(ex.exerciseId)">
                            <font-awesome-icon :icon="['fa', 'trash']"/>
                        </button>
                        <button class="btn-white mx-2" data-bs-toggle="modal" href="#infoExerciseModal"
                                @click="openInfoModal(ex)">
                            <font-awesome-icon :icon="['fa', 'info']"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <ExerciseDetails :exercise="exercise" @submit:editExercise="submitEditExercise"/>
    </div>
</template>

<script>
import ExerciseDetails from "@/components/sport/exercise/ExerciseDetails";

export default {
    name: "ExercisesListComponent",
    components: {ExerciseDetails},
    data() {
        return {
            exercises: [this.exercise],
            exercise: {
                exerciseId: 453,
                name: "Martwy ciąg",
                exerciseType: "Siłowe",
                caloriesBurned: 345,
                owner: ""
            },
            enableButtons: false,
            opened: [],
        }

    },
    props: {
        exercisesSource: Array,
        mode: String,
        inModal: Boolean
    },
    methods: {
        submitEditExercise(exercise, labels) {
            this.$emit('submit:editExercise', exercise, labels)
        },
        openInfoModal(exercise) {
            this.exercise = exercise
            // this.$store.commit('setExerciseId', id);
            // this.$refs.datailsModal.getExercise();
        },
        toggle(id) {
            const index = this.opened.indexOf(id);
            if (index > -1) {
                this.opened.splice(index, 1)
            } else {
                this.opened.push(id)
            }
        },
        deleteExercise(exerciseId) {
            this.$emit('delete:exercise', exerciseId)

        }
    }

}
</script>

<style scoped>

.opened {
    background-color: yellow;
}

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}

th, td {
    color: white;
}

table {
    --bs-table-hover-color: none;
    color: white;
    border-bottom: none;
}

.selected-exercise {
    background-color: var(--SPORT);
}

</style>
