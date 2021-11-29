<template>
    <div class="tab-content">
        <dietician-labels-component/>
    </div>
</template>

<script>
import DieticianLabelsComponent from '../../../components/diet/labels/DieticianLabelsComponent.vue'
export default {
    name: "DieticianDishesView",
    components: {
        DieticianLabelsComponent
    }
}
</script>
<style scoped>
</style>