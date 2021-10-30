<template>
    <div>
        <div class="add-exercise row my-2 align-items-center">
            <span class="h3 col-8 offset-2 text-end">Dodaj</span>
            <span class="col-2 float-end">
                <font-awesome-icon class="icon  mx-4" :icon="['fa', 'plus-circle']" data-bs-toggle="modal" data-bs-target="#addExerciseModal" />
            </span>
        </div>
        <ExercisesListComponent :exercises-source="exercises"/>
        <ExercisesGridComponent :exercises-source="exercises"/>
        <!--Modal-->
        <ExerciseForm :labels-source="labels" @get:exercises="getExercises"/>
    </div>
</template>

<script>
import ExercisesListComponent from "@/components/sport/exercise/ExercisesListComponent";
import ExercisesGridComponent from "@/components/sport/exercise/ExercisesGridComponent";
import ExerciseForm from "@/components/sport/exercise/ExerciseForm";
export default {
    name: "ExerciseView",
    components: {
        ExerciseForm,
        ExercisesGridComponent,
        ExercisesListComponent,
    },
    data () {
        return {
            exercises: [],
            labels: [],
        }
    },
    methods: {
        async getExercises () {
            const url = `${this.apiURL}sport/exercise`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.exercises = response.data['objects']
                console.log(this.exercises)
            }).catch(error => {
                console.log(error.response);
            });
        },
        async getLabels () {
            const url = `${this.apiURL}sport/exercise/labels`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.labels = response.data
                console.log(this.labels)
            }).catch(error => {
                console.log(error.response);
            });
        }
    },
    mounted() {
        this.getExercises();
        this.getLabels();
    }

}
</script>

<style scoped>
.icon {
    font-size: 30px;
}
.sport-tabs .active{
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}
.sport-tabs {
    border-bottom-color: var(--SPORT);
}
</style>
