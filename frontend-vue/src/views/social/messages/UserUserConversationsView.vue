<template>
    <div class="section-bg">
        <div class="container">
            <div class="row text-start pt-4">
                <h4>Wiadomości</h4>
            </div>
            <div class="row">
                <div class="col mx-auto">
                    <conversations-component connection-type="WITH_USER" :as-specialist="false"
                                             @open-conversation="openConversation"/>
                </div>
            </div>

        </div>

    </div>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";

export default {
    name: "UserUserConversationsView",
    components: {
        ConversationsComponent
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'UserUserConversationView', params: { conversationId: conversationId } });
        }
    },
}
</script>

<style scoped>

</style>