<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <user-connections-from-me-component connection-type="WITH_DOCTOR"
                                                v-on:open-profile="openProfile"
                                                v-on:open-conversation="openConversation"
                                                v-on:search-specialist="openSearchSpecialist"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/navbars/TelemedicNavbar";
import UserConnectionsFromMeComponent from "@/components/telemedic/user-connections/UserConnectionsFromMeComponent";

export default {
    name: 'UserDoctorsView',
    components: {
        TelemedicNavbar,
        UserConnectionsFromMeComponent
    },
    data() {
        return {

        }
    },
    methods: {
        openProfile(profileId){
            this.$router.push({ name: 'ProfileView', params: { profileId: profileId } });
        },
        openConversation(conversationId){
            this.$router.push({ name: 'UserDoctorConversationView', params: { conversationId: conversationId } });
        },
        openSearchSpecialist(){
            this.$router.push({ name: 'SearchDoctorsView' });
        },
    },
    created(){

    },
}
</script>

<style scoped>

</style>