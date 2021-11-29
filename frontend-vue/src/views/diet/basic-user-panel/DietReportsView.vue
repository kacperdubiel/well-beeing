<template>
    <div class="tab-content">
        <profile-reports-component></profile-reports-component>
    </div>
</template>

<script>
import ProfileReportsComponent from '../../../components/diet/reports/ProfileReportsComponent.vue'
export default {
    name: "DietReportsView",
    components: {
        ProfileReportsComponent
    }
}
</script>

<style scoped>

</style>