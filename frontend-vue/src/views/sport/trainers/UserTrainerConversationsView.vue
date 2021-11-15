<template>
    <div>

        <section>
            <conversations-component connection-type="WITH_TRAINER" :as-specialist="false" v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/ConversationsComponent";

export default {
    name: "UserTrainerConversationsView",
    components: {
        ConversationsComponent},
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'UserTrainerConversationView', params: { conversationId: conversationId } });
        }
    }
}
</script>

<style scoped>

</style>
