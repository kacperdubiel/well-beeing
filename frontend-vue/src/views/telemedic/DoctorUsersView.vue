<template>
    <div class="section-bg telemedic-section">
        <header>
            <doctor-navbar/>
        </header>
        <section>
            <user-connections-to-me-component connection-type="WITH_DOCTOR"
                                              v-on:open-profile="openProfile" v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import DoctorNavbar from "@/components/telemedic/DoctorNavbar";
import UserConnectionsToMeComponent from "@/components/telemedic/UserConnectionsToMeComponent";

export default {
    name: 'DoctorUsersView',
    components: {
        DoctorNavbar,
        UserConnectionsToMeComponent
    },
    data() {
        return {

        }
    },
    methods: {
        openProfile(profileId){
            this.$router.push({ name: 'DoctorUserProfileView', params: { userId: profileId } });
        },
        openConversation(conversationId){
            this.$router.push({ name: 'DoctorUserConversationView', params: { conversationId: conversationId } });
        },
    },
    created(){

    },
}
</script>

<style scoped>

</style>