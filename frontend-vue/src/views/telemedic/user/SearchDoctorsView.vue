<template>
    <div>
        <search-specialist-component connection-type="WITH_DOCTOR" v-on:open-profile="openProfile"/>
    </div>
</template>

<script>
import SearchSpecialistComponent from "@/components/telemedic/user-connections/SearchSpecialistComponent";

export default {
    name: 'UserDoctorsView',
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
    created(){

    },
}
</script>

<style scoped>

</style>