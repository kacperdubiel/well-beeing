<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <measures-component :user-id="this.userId"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/navbars/TelemedicNavbar";
import MeasuresComponent from "@/components/telemedic/MeasuresComponent";

export default {
    name: 'UserMeasuresView',
    components: {
        MeasuresComponent,
        TelemedicNavbar
    },
    data() {
        return {
            userId: this.$store.getters.getProfileId,
        }
    },
    methods: {

    },
    created() {

    },
}
</script>

<style scoped>

</style>