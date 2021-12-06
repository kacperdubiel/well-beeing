<template>
    <div>
        <div class="section-bg pt-4">
            <header>

            </header>
            <section>
                <conversation-component :conversation-id="conversationId" />
            </section>
        </div>
    </div>
</template>

<script>
import ConversationComponent from "@/components/telemedic/communicator/ConversationComponent";

export default {
    name: "UserUserConversationView",
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