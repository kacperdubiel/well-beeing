<template>
    <div class="section-bg p-3">
        <ul class="nav nav-tabs diet-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'DieticianUsersView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DieticianUsersView' }">Podopieczni</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DieticianDishesView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DieticianDishesView' }">Dania</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DieticianLabelsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DieticianLabelsView' }">Etykiety</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DieticianUserAllConversationsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'DieticianUserAllConversationsView' || this.$route.name === 'DieticianUserConversationView'  }">Wiadomości</span>
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
    width: 120px;
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
