<template>
    <div class="tab-content">
        <conversation-component :conversation-id="conversationId" />
    </div>
</template>

<script>
import ConversationComponent from "@/components/telemedic/communicator/ConversationComponent";
export default {
    name: "UserDieticianConversationView",
    components: {
       ConversationComponent
    },
    data() {
        return {
            conversationId: this.$route.params.conversationId,
        }
    },
}
</script>

<style scoped>
</style>