<template>
    <div class="telemedic-section">
        <section>
            <user-connections-to-me-component connection-type="WITH_TRAINER"
                                              v-on:open-profile="openProfile" v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import UserConnectionsToMeComponent from "@/components/telemedic/user-connections/UserConnectionsToMeComponent";

export default {
    name: 'DoctorUsersView',
    components: {
        UserConnectionsToMeComponent
    },
    data() {
        return {

        }
    },
    methods: {
        openProfile(profileId){
            this.$router.push({ name: 'TrainerUserProfileView', params: { userId: profileId } });
        },
        openConversation(conversationId){
            this.$router.push({ name: 'TrainerUserConversationView', params: { conversationId: conversationId } });
        },
    },
    created(){

    },
}
</script>

<style scoped>

</style>
