<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <conversations-component connection-type="WITH_DOCTOR" :as-specialist="false" v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/navbars/TelemedicNavbar";
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";

export default {
    name: 'UserDoctorConversationsView',
    components: {
        ConversationsComponent,
        TelemedicNavbar
    },
    data() {
        return {
        }
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'UserDoctorConversationView', params: { conversationId: conversationId } });
        }
    },
    created(){

    },
}
</script>

<style scoped>

</style>