<template>
    <div class="section-bg telemedic-section">
        <header>
            <doctor-navbar/>
        </header>
        <section>
            <conversations-component connection-type="WITH_DOCTOR" :as-specialist="true"
                                     v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import DoctorNavbar from "@/components/telemedic/DoctorNavbar";
import ConversationsComponent from "@/components/telemedic/ConversationsComponent";

export default {
    name: 'DoctorUserConversationsView',
    components: {
        DoctorNavbar,
        ConversationsComponent
    },
    data() {
        return {
        }
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'DoctorUserConversationView', params: { conversationId: conversationId } });
        }
    },
    created(){

    },
}
</script>

<style scoped>

</style>