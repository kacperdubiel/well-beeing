<template>
    <div>
        <section>
            <user-connections-to-me-component connection-type="WITH_USER"
                                              v-on:open-profile="openProfile"
                                              v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import UserConnectionsToMeComponent from "@/components/telemedic/user-connections/UserConnectionsToMeComponent";

export default {
    name: "FriendsInvitationsReceivedView",
    components: {
        UserConnectionsToMeComponent
    },
    methods:{
        openProfile(profileId){
            this.$router.push({ name: 'ProfileView', params: { profileId: profileId } });
        },
        openConversation(conversationId){
            this.$router.push({ name: 'UserUserConversationView', params: { conversationId: conversationId } });
        },
    }
}
</script>

<style scoped>

</style>