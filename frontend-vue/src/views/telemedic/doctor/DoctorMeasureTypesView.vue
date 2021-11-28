<template>
    <div class="section-bg telemedic-section">
        <header>
            <doctor-navbar/>
        </header>
        <section>
            <measure-types-component />
        </section>
    </div>
</template>

<script>
import DoctorNavbar from "@/components/telemedic/navbars/DoctorNavbar";
import MeasureTypesComponent from "@/components/telemedic/measures/MeasureTypesComponent";

export default {
    name: 'DoctorMeasureTypesView',
    components: {
        DoctorNavbar,
        MeasureTypesComponent
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