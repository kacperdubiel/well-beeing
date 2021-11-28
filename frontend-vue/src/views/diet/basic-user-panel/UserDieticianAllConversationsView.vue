<template>
    <div class="tab-content">
        <conversations-component connection-type="WITH_DIETICIAN" :as-specialist="false" v-on:open-conversation="openConversation"/>
    </div>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";
export default {
    name: "UserDieticianConversationView",
    components: {
        ConversationsComponent
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'UserDieticianConversationView', params: { conversationId: conversationId } });
        }
    },
}
</script>

<style scoped>
</style>