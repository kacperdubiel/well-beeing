<template>
    <div class="section-bg p-4  sport-tabs">
        <ul class="nav nav-tabs">
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
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'SportReportsComponent' }">Raporty</span>
                </router-link>
            </li>
            <li class="nav-item item-link">
                <router-link :to="{ name: 'UserTrainersView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'UserTrainersView' }">Trenerzy</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'SportView' }">
                    <span class="nav-link">Analizy</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'UserTrainerConversationsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'UserTrainerConversationsView' || this.$route.name === 'UserTrainerConversationView' }">Wiadomości</span>
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
.sport-tabs {
    border-bottom-color: var(--SPORT);
}

.sport-tabs{
    margin-bottom: 20px;
}

.sport-tabs a {
    text-decoration: none;
}

.sport-tabs .nav-link.active {
    background-color: var(--SPORT);
    border-color: var(--SPORT);
}

.sport-tabs .nav-link {
    color: white;
    font-weight: bold;
}

.sport-tabs .nav-tabs {
    border-bottom-color: var(--SPORT);
}

.sport-tabs .nav-link:hover {
    border-color: var(--SPORT);
}

a {
    color: white;
    text-decoration: none;
}
</style>
