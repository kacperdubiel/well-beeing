<template>
    <div class="section-bg p-4  social-tabs">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'MyFriendsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'MyFriendsView' }">Moi znajomi</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'FriendsInvitationsSentView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'FriendsInvitationsSentView' }">Wysłane zaproszenia</span>
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'FriendsInvitationsReceivedView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'FriendsInvitationsReceivedView' }">Otrzymane zaproszenia</span>
                </router-link>
            </li>
        </ul>
        <!--        <p>{{this.$route.name}}</p>-->
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "FriendsView"
}
</script>

<style scoped>
.social-tabs {
    border-bottom-color: var(--DARK-YELLOW);
    margin-bottom: 20px;
}

.social-tabs a {
    text-decoration: none;
}

.social-tabs .nav-link.active {
    background-color: var(--DARK-YELLOW);
    border-color: var(--DARK-YELLOW);
}

.social-tabs .nav-link {
    color: white;
    font-weight: bold;
}

.social-tabs .nav-tabs {
    border-bottom-color: var(--DARK-YELLOW);
}

.social-tabs .nav-link:hover {
    border-color: var(--DARK-YELLOW);
}

a {
    color: white;
    text-decoration: none;
}
</style>