<template>
    <div class="tab-content">
        <calculations-component></calculations-component>
    </div>
</template>

<script>
import CalculationsComponent from '../../../components/diet/calc/CalculationsComponent.vue'
export default {
    name: "DietCalculationsView",
    components: {
        CalculationsComponent
    }
}
</script>

<style scoped>

</style>