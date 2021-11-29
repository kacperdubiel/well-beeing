<template>
    <div>
        <conversation-component :conversation-id="conversationId" />
    </div>
</template>

<script>
import ConversationComponent from "@/components/telemedic/communicator/ConversationComponent";

export default {
    name: 'UserDoctorConversationView',
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