<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <div class="container">
                Analiza
            </div>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/TelemedicNavbar";

export default {
    name: 'AnalysisView',
    components: {
        TelemedicNavbar
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
    .telemedic-section {
        padding: 20px;
    }
</style>