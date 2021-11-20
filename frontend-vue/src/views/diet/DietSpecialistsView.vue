<template>
    <div class="tab-content">
        <user-connections-from-me-component connection-type="WITH_DIETICIAN"
                                                v-on:open-conversation="openConversation"
                                                v-on:search-specialist="openSearchSpecialist"/>
    </div>
</template>

<script>
import UserConnectionsFromMeComponent from "@/components/telemedic/UserConnectionsFromMeComponent.vue";
export default {
    name: "DietSpecialistsView",
    components: {
        UserConnectionsFromMeComponent,
    },
    methods: {
        openConversation(conversationId){
            this.$router.push({ name: 'UserDieticianConversationView', params: { conversationId: conversationId } });
        },
        openSearchSpecialist(){
            this.$router.push({ name: 'DietSpecialistsSearchView' });
        },
    },
}
</script>

<style scoped>

</style>