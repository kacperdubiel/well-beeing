<template>
    <div>
        <conversations-component connection-type="WITH_DOCTOR" :as-specialist="false"
                                 v-on:open-conversation="openConversation"/>
    </div>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";

export default {
    name: 'UserDoctorConversationsView',
    components: {
        ConversationsComponent
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