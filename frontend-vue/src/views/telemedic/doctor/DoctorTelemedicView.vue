<template>
    <div class="section-bg telemedic-section">
        <header>
            <doctor-navbar/>

        </header>
        <section>
            <router-view></router-view>
        </section>
    </div>
</template>

<script>
import DoctorNavbar from "@/components/telemedic/navbars/DoctorNavbar";

export default {
    name: "DoctorTelemedicView",
    components: {
        DoctorNavbar
    },
}
</script>

<style scoped>

</style>