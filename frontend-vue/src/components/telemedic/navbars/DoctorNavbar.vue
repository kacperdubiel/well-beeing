<template>
    <div class="doctor-navbar">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <router-link :to="{ name: 'DoctorUsersView' }" class="nav-link" active-class="active"
                             v-bind:class="{ active: this.$route.name === 'DoctorUsersView' || this.$route.name === 'DoctorUserProfileView' }"
                >
                    Pacjenci
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DoctorUserConversationsView' }" class="nav-link" active-class="active"
                             v-bind:class="{ active: this.$route.name === 'DoctorUserConversationsView' || this.$route.name === 'DoctorUserConversationView' }"
                >
                    Wiadomości
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'DoctorMeasureTypesView' }" class="nav-link" active-class="active">
                    Typy pomiarów
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "DoctorNavbar"
}
</script>

<style scoped>
    .doctor-navbar{
        margin-bottom: 20px;
    }

    .doctor-navbar a {
        text-decoration: none;
    }

    .doctor-navbar .nav-link.active {
        background-color: var(--TELEMEDIC);
        border-color: var(--TELEMEDIC);
    }

    .doctor-navbar .nav-link {
        color: white;
        font-weight: bold;
    }

    .doctor-navbar .nav-tabs {
        border-bottom-color: var(--TELEMEDIC);
    }

    .doctor-navbar .nav-link:hover {
        border-color: var(--TELEMEDIC);
    }

</style>