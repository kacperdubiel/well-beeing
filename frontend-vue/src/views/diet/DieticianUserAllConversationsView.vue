<template>
    <div class="tab-content">
        <conversations-component connection-type="WITH_DIETICIAN" :as-specialist="true"
                                     v-on:open-conversation="openConversation"/>
    </div>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";
export default {
    name: "DieticianUserAllConversationsView",
    components: {
        ConversationsComponent
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'DieticianUserConversationView', params: { conversationId: conversationId } });
        }
    },
}
</script>

<style scoped>

</style>