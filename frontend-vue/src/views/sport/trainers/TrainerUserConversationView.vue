<template>
    <div class="telemedic-section">
        <header>

        </header>
        <section>
            <conversation-component :conversation-id="conversationId" />
        </section>
    </div>
</template>

<script>
import ConversationComponent from "@/components/telemedic/ConversationComponent";

export default {
    name: 'TrainerUserConversationView',
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
    created(){
    },
}
</script>

<style scoped>

</style>
