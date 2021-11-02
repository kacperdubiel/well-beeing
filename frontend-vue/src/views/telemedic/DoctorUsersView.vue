<template>
    <div class="section-bg telemedic-section">
        <header>

        </header>
        <section>
            <specialist-connections-component connection-type="WITH_DOCTOR"/>
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

    },
    created(){

    },
}
</script>

<style scoped>

</style>