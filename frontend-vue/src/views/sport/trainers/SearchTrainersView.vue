<template>
<div>
    <section>
        <search-specialist-component class="m-3" connection-type="WITH_TRAINER" v-on:open-profile="openProfile"/>
    </section>
</div>
</template>

<script>
import SearchSpecialistComponent from "@/components/telemedic/user-connections/SearchSpecialistComponent";

export default {
    name: "SearchTrainersView",
    components: {
        SearchSpecialistComponent
    },
    data() {
        return {

        }
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
