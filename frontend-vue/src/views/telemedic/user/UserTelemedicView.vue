<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <router-view></router-view>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/navbars/TelemedicNavbar";

export default {
    name: "UserTelemedicView",
    components: {
        TelemedicNavbar
    },
}
</script>

<style scoped>

</style>