<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <user-connections-component connection-type="WITH_DOCTOR"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/TelemedicNavbar";
import UserConnectionsComponent from "@/components/telemedic/UserConnectionsComponent";

export default {
    name: 'UserDoctorsView',
    components: {
        TelemedicNavbar,
        UserConnectionsComponent
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