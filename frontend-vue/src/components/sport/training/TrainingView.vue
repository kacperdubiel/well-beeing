<template>

<!--<div class="section-bg p-3">-->
<!--    <ul class="nav nav-tabs sport-tabs">-->
<!--        <li class="nav-item">-->
<!--            <router-link :to="{ name: 'SportView' }">-->
<!--                <span class="nav-link">Plany treningowe</span>-->
<!--            </router-link>-->
<!--        </li>-->
<!--        <li class="nav-item">-->
<!--            <router-link :to="{ name: 'ExerciseView' }">-->
<!--                <span class="nav-link">Ćwiczenia</span>-->
<!--            </router-link>-->
<!--        </li>-->
<!--        <li class="nav-item">-->
<!--            <router-link :to="{ name: 'TrainingView' }">-->
<!--                <span class="nav-link active" aria-current="page" >Treningi</span>-->
<!--            </router-link>-->
<!--        </li>-->
<!--        <li class="nav-item">-->
<!--            <router-link :to="{ name: 'SportView' }">-->
<!--                <span class="nav-link">Aktywność</span>-->
<!--            </router-link>-->
<!--        </li>-->
<!--        <li class="nav-item item-link">-->
<!--            <router-link :to="{ name: 'SportView' }">-->
<!--                <span class="nav-link">Trenerzy</span>-->
<!--            </router-link>-->
<!--        </li>-->
<!--        <li class="nav-item">-->
<!--            <router-link :to="{ name: 'SportView' }">-->
<!--                <span class="nav-link">Analizy</span>-->
<!--            </router-link>-->
<!--        </li>-->
<!--    </ul>-->

    <div>
        <div class="add-exercise row my-2 align-items-center">
            <span class="h3 col-8 offset-2 text-end">Dodaj</span>
            <span class="col-2 float-end">
                <font-awesome-icon class="icon  mx-4" :icon="['fa', 'plus-circle']" data-bs-toggle="modal" data-bs-target="#addExerciseModal" />
            </span>
        </div>
        <TrainingsListComponent :trainings-source="trainings"/>
        <TrainingsGridComponent :trainings-source="trainings"/>
        <!--Modal-->
        <TrainingForm :labels-source="labels" :exercise-source="exercises" @get:exercises="getExercises" @get:trainings="getTrainings"/>
    </div>
<!--</div>-->
</template>

<script>
import TrainingForm from "@/components/sport/training/TrainingForm";
import TrainingsGridComponent from "@/components/sport/training/TrainingsGridComponent";
import TrainingsListComponent from "@/components/sport/training/TrainingsListComponent";

export default {
    name: "TrainingView",
    components: {
        TrainingForm,
        TrainingsGridComponent,
        TrainingsListComponent,
    },
    data () {
        return {
            trainings: [],
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
        async getTrainings () {
            const url = `${this.apiURL}sport/training`
            const token = this.$store.getters.getToken;
            console.log('token ', token);
            await this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.trainings = response.data['objects']
                console.log(this.trainings)
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
        this.getTrainings();
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
