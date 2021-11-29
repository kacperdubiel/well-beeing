<template>
    <div class="section-bg p-3">
        <ul class="nav nav-tabs diet-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'DietCardView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DietCardView' }">Karta</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DietCalculationsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DietCalculationsView' }">Kalkulacje</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DietReportsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DietReportsView' }">Raporty</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'UserNutritionPlansView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'UserNutritionPlansView'}">Plany</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DietDishBrowserView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DietDishBrowserView' }">Dania</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DietSpecialistsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DietSpecialistsView' || this.$route.name === 'DietSpecialistsSearchView' }">Dietetycy</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'UserDieticianAllConversationsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'UserDieticianAllConversationsView' || this.$route.name === 'UserDieticianConversationView'  }">Wiadomości</span>
                </router-link>
            </li>
        </ul>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "DietViewMain",
    components: {},
    data () {
    },
    methods: {

    },
}

</script>

<style scoped>
.diet-tabs .active{
    background-color: var(--DIET);
    border-color: var(--DIET);
    color: white;
}
.diet-tabs {
    border-bottom-color: var(--DIET);
}
.diet-tabs li {
    margin-left: 0px;
    width: 118px;
    text-align: center;
    padding: 20px, 20px;
    margin-right: 1px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    cursor: pointer;
    transition: 0.4s all ease-out;
}
a {
    color: white;
    text-decoration: none;
}
.nav-link {
    color: white;

}
.nav-link:hover {
    background-color: var(--DIET);
    border-color: var(--DIET);
}
</style>
