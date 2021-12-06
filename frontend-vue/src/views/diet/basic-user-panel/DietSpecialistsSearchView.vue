<template>
    <div class="tab-content">
         <search-specialist-component connection-type="WITH_DIETICIAN" v-on:open-profile="openProfile"/>
    </div>
</template>

<script>
import SearchSpecialistComponent from "@/components/telemedic/user-connections/SearchSpecialistComponent.vue";
export default {
    name: "DietSpecialistsSearchView",
    components: {
        SearchSpecialistComponent
    },
    methods: {
        openProfile(profileId){
            this.$router.push({ name: 'ProfileView', params: { profileId: profileId } });
        },
    },
}
</script>

<style scoped>

</style>