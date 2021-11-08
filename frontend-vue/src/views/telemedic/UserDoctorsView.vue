<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <user-connections-component connection-type="WITH_DOCTOR" v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/TelemedicNavbar";
import UserConnectionsComponent from "@/components/telemedic/UserConnectionsComponent";

export default {
    name: 'UserDoctorsView',
    components: {
        TelemedicNavbar,
        UserConnectionsComponent
    },
    data() {
        return {

        }
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'UserDoctorConversationView', params: { conversationId: conversationId } });
        },
    },
    created(){

    },
}
</script>

<style scoped>

</style>