<template>
    <div class="telemedic-navbar">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'UserMeasuresView' }" class="nav-link" active-class="active">
                    Pomiary
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'UserDoctorsView' }" class="nav-link" active-class="active"
                             v-bind:class="{ active: this.$route.name === 'UserDoctorsView' || this.$route.name === 'SearchDoctorsView' }">
                    Lekarze
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'UserDoctorConversationsView' }" class="nav-link" active-class="active"
                             v-bind:class="{ active: this.$route.name === 'UserDoctorConversationsView' || this.$route.name === 'UserDoctorConversationView' }"
                >
                    Wiadomości
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'AnalysisView' }" class="nav-link" active-class="active">
                    Analizy
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "TelemedicNavbar"
}
</script>

<style scoped>
    .telemedic-navbar{
        margin-bottom: 20px;
    }

    .telemedic-navbar a {
        text-decoration: none;
    }

    .telemedic-navbar .nav-link.active {
        background-color: var(--TELEMEDIC);
        border-color: var(--TELEMEDIC);
    }

    .telemedic-navbar .nav-link {
        color: white;
        font-weight: bold;
    }

    .telemedic-navbar .nav-tabs {
        border-bottom-color: var(--TELEMEDIC);
    }

    .telemedic-navbar .nav-link:hover {
        border-color: var(--TELEMEDIC);
    }

</style>