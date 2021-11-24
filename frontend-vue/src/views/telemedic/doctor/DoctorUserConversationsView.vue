<template>
    <div>
        <conversations-component connection-type="WITH_DOCTOR" :as-specialist="true"
                                 v-on:open-conversation="openConversation"/>
    </div>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";

export default {
    name: 'DoctorUserConversationsView',
    components: {
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