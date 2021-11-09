<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <search-specialist-component connection-type="WITH_DOCTOR"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/TelemedicNavbar";
import SearchSpecialistComponent from "@/components/telemedic/SearchSpecialistComponent";

export default {
    name: 'UserDoctorsView',
    components: {
        TelemedicNavbar,
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