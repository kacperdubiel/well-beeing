<template>
        <section>
            <conversations-component connection-type="WITH_TRAINER" :as-specialist="true"
                                     v-on:open-conversation="openConversation"/>
        </section>
</template>

<script>
import ConversationsComponent from "@/components/telemedic/communicator/ConversationsComponent";

export default {
    name: 'TrainerUserConversationsView',
    components: {
        ConversationsComponent
    },
    data() {
        return {
        }
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'TrainerUserConversationView', params: { conversationId: conversationId } });
        }
    },
    created(){

    },
}
</script>

<style scoped>

</style>
