<template>
    <div class="section-bg p-4  sport-tabs">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'TrainerUsersView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'TrainerUsersView' }">Podopieczni</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'TrainerUserConversationsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'TrainerUserConversationsView' || this.$route.name === 'TrainerUserConversationView' }">Wiadomości</span>
                </router-link>
            </li>
        </ul>
        <!--        <p>{{this.$route.name}}</p>-->
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "SportTrainerView"
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
