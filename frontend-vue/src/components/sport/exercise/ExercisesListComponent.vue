<template>
<div class="container my-3">
    <div class="row justify-content-center">
        <div class="col-md-7 col-sm-6 align-self-center">
            <input
                type="text"
                placeholder="Wyszukaj..."
                id="search-input"
                class="w-100 shadow"
            />
        </div>
        <div class="col-md-5 col-sm-6 align-self-center">
            FILTRY
        </div>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Typ</th>
                    <th scope="col">kcal/h</th>
                    <th scope="col">Twórca</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="ex in exercisesSource" :key="ex.name">
                    <td>{{ ex.exerciseId }}</td>
                    <td>{{ ex.name }}</td>
                    <td>{{ ex.exerciseType }}</td>
                    <td>{{ ex.caloriesBurned }}</td>
                    <td>{{ ex.owner }}</td>
                    <td>
                        <button class="btn-white mx-2" @click="openInfoModal(ex.exerciseId)" data-bs-toggle="modal" data-bs-target="#infoExerciseModal">
                            <font-awesome-icon :icon="['fa', 'info']" />
                        </button>
                        <button class="btn-white" v-if="enableButtons">
                            <font-awesome-icon :icon="['fa', 'pencil-alt']" />
                        </button>
                        <button class="btn-white mx-2" v-if="enableButtons">
                            <font-awesome-icon :icon="['fa', 'trash']" />
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <ExerciseDetails ref="datailsModal"/>
</div>
</template>

<script>
import ExerciseDetails from "@/components/sport/exercise/ExerciseDetails";
export default {
    name: "ExercisesListComponent",
    components: {ExerciseDetails},
    data () {
        return {
            exercises: [this.exercise],
            exercise: {
                exerciseId:453,
                name:"Martwy ciąg",
                exerciseType:"Siłowe",
                caloriesBurned:345,
                owner:""
            },
            enableButtons: false
        }

    },
    props: {
        exercisesSource: Array
    },
    methods: {
        openInfoModal(id) {
            this.$store.commit('setExerciseId', id);
            this.$refs.datailsModal.getExercise();
        }
    }
}
</script>

<style scoped>
.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}
th, td {
    color: var(--bs-white);
}
table {
    --bs-table-hover-color: none;
    color: white;
    border-bottom: none;
}
</style>
