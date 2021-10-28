<template>
    <div class="section-bg p-3">
        <ul class="nav nav-tabs sport-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link active" aria-current="page" >Plany treningowe</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Ćwiczenia</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Treningi</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Aktywność</span>
                </router-link>
            </li>
            <li class="nav-item item-link">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Trenerzy</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Analizy</span>
                </router-link>
            </li>
        </ul>
        <TrainingPlans></TrainingPlans>
        <ExercisesComponent></ExercisesComponent>
    </div>
</template>

<script>
import TrainingPlans from "@/components/sport/TrainingPlansComponent";
import ExercisesComponent from "@/components/sport/exercise/ExercisesComponent";
export default {
    name: "SportComponent",
    components: {ExercisesComponent, TrainingPlans},
    data () {
        return {
            week:43,
            year:2021,
            days:['Poniedziałek', 'Wtorek', 'Środa', 'Czwartek', 'Piątek', 'Sobota', 'Niedziela']
        }
    },
    methods: {

    },
}

</script>

<style scoped>
.sport-tabs .active{
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}
.sport-tabs {
    border-bottom-color: var(--SPORT);
}
</style>