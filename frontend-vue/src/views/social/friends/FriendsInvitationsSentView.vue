<template>
    <div>
        <section>
            <user-connections-from-me-component connection-type="WITH_USER"
                                                v-on:open-profile="openProfile"
                                                v-on:open-conversation="openConversation"
                                                v-on:search-specialist="function (){}"/>
        </section>
    </div>
</template>

<script>
import UserConnectionsFromMeComponent from "@/components/telemedic/user-connections/UserConnectionsFromMeComponent";

export default {
    name: "FriendsInvitationsSentView",
    components:{UserConnectionsFromMeComponent},
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