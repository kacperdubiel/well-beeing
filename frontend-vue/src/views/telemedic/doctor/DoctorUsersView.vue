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
import DoctorNavbar from "@/components/telemedic/navbars/DoctorNavbar";
import UserConnectionsToMeComponent from "@/components/telemedic/user-connections/UserConnectionsToMeComponent";

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
        openProfile(profileId, isAccepted){
            if(isAccepted){
                this.$router.push({ name: 'DoctorUserProfileView', params: { userId: profileId } });
            } else {
                this.$router.push({ name: 'ProfileView', params: { profileId: profileId } });
            }
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