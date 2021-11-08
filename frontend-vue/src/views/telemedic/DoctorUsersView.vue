<template>
    <div class="section-bg telemedic-section">
        <header>

        </header>
        <section>
            <specialist-connections-component connection-type="WITH_DOCTOR"
                                              v-on:open-profile="openProfile" v-on:open-conversation="openConversation"/>
        </section>
    </div>
</template>

<script>
import SpecialistConnectionsComponent from "@/components/telemedic/SpecialistConnectionsComponent";

export default {
    name: 'DoctorUsersView',
    components: {
        SpecialistConnectionsComponent
    },
    data() {
        return {

        }
    },
    methods: {
        openProfile(profileId){
            this.$router.push({ name: 'DoctorUserProfileView', params: { userId: profileId } });
        },
        openConversation(conversationId){
            this.$router.push({ name: 'DoctorUserConversationView', params: { conversationId: conversationId } });
        },
    },
    created(){

    },
}
</script>

<style scoped>

</style>