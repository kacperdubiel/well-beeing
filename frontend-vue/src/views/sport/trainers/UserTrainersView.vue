<template>
    <div>
        <section class="mt-3">
            <user-connections-from-me-component class="mt-3" connection-type="WITH_TRAINER"
                                                v-on:open-profile="openProfile"
                                                v-on:open-conversation="openConversation"
                                                v-on:search-specialist="openSearchSpecialist"/>
        </section>
    </div>
</template>

<script>
import UserConnectionsFromMeComponent from "@/components/telemedic/user-connections/UserConnectionsFromMeComponent";

export default {
    name: "UserTrainersView",
    components: {UserConnectionsFromMeComponent},
    methods: {
        openProfile(profileId) {
            this.$router.push({name: 'ProfileView', params: {profileId: profileId}});
        },
        openConversation(conversationId) {
            this.$router.push({name: 'UserTrainerConversationView', params: {conversationId: conversationId}});
        },
        openSearchSpecialist() {
            this.$router.push({name: 'SearchTrainersView'});
        },
    }
}
</script>

<style scoped>

</style>
