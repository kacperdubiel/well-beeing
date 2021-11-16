<template>
<div>
    <section>
        <conversation-component :conversation-id="conversationId" />
    </section>
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
