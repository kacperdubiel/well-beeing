<template>
    <div class="section-bg telemedic-section">
        <search-specialist-component connection-type="WITH_DOCTOR"/>
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

    },
    created(){

    },
}
</script>

<style scoped>

</style>