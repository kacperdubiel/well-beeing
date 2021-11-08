<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <user-connections-from-me-component connection-type="WITH_DOCTOR"
                                                v-on:open-conversation="openConversation"
                                                v-on:search-specialist="openSearchSpecialist"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/TelemedicNavbar";
import UserConnectionsFromMeComponent from "@/components/telemedic/UserConnectionsFromMeComponent";

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
        openConversation(conversationId){
            this.$router.push({ name: 'UserDoctorConversationView', params: { conversationId: conversationId } });
        },
        openSearchSpecialist(){
            this.$router.push({ name: 'SearchDoctorView' });
        },
    },
    created(){

    },
}
</script>

<style scoped>

</style>