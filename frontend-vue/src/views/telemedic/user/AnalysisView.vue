<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <analysis-component :user-id="this.userId" />
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/navbars/TelemedicNavbar";
import AnalysisComponent from "@/components/telemedic/analysis/AnalysisComponent";

export default {
    name: 'AnalysisView',
    components: {
        AnalysisComponent,
        TelemedicNavbar
    },
    data() {
        return {
            userId: this.$store.getters.getProfileId,
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