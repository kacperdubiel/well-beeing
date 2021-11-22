<template>
<div>
    <div class="telemedic-section">
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
    name: "UserTrainerConversationView",
    components: {
        ConversationComponent
    },
    data() {
        return {
            conversationId: this.$route.params.conversationId,
        }
    },
    methods: {

    },
}
</script>

<style scoped>

</style>
