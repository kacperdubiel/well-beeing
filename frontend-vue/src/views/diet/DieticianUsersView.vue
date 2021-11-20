<template>
    <div class="tab-content">
        <user-connections-to-me-component connection-type="WITH_DIETICIAN"
                                              v-on:open-profile="openProfile" v-on:open-conversation="openConversation"/>
    </div>
</template>

<script>
import UserConnectionsToMeComponent from "@/components/telemedic/UserConnectionsToMeComponent.vue";
export default {
    name: "DieticianUsersView",
    components: {
        UserConnectionsToMeComponent,
    },
    methods: {
        openProfile(profileId){
           this.$router.push({ name: 'DieticianUserProfileView', params: { userId: profileId } });
        },
        openConversation(conversationId){
            this.$router.push({ name: 'DieticianUserConversationView', params: { conversationId: conversationId } });
        },
    },
}
</script>

<style scoped>

</style>