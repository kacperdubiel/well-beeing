<template>
    <div class="section-bg p-3">
        <ul class="nav nav-tabs sport-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'TrainingPlansView' }">Plany treningowe</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'ExerciseView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'ExerciseView' }">Ćwiczenia</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'TrainingView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'TrainingView' }">Treningi</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportReportsComponent' }">
                    <span class="nav-link">Raporty</span>
                </router-link>
            </li>
            <li class="nav-item item-link">
                <router-link :to="{ name: 'UserTrainersView' }">
                    <span class="nav-link">Trenerzy</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Analizy</span>
                </router-link>
            </li>
        </ul>
<!--        <p>{{this.$route.name}}</p>-->
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "SportComponent",
    components: {},
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
    color: white;
}
.sport-tabs {
    border-bottom-color: var(--SPORT);
}
a {
    color: white;
    text-decoration: none;
}
.nav-link {
    color: white;
    border-color: var(--SPORT);

}
.nav-link:hover {
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}
</style>
