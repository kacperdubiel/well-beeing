<template>
    <div class="section-bg p-4  social-tabs">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'MyFollowingsView' }">
                    <span class="nav-link" v-bind:class="{ active: this.$route.name === 'MyFollowingsView' }">Obserwowani</span>
                </router-link>
            </li>
            <li class="nav-item" v-if="isSpecialist">
                <router-link :to="{ name: 'MyFollowersView' }">
                    <span class="nav-link" :class="{active: this.$route.name === 'MyFollowersView'}">
                        Obserwujący
                    </span>
                </router-link>
            </li>
        </ul>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "FollowView",
    computed: {
        isSpecialist() {
            return this.$store.getters.getDoctorProfile != null || this.$store.getters.getDieticianProfile != null || this.$store.getters.getTrainerProfile != null
        }
    },
}
</script>

<style scoped>
a {
    color: white;
    text-decoration: none;
}
</style>