<template>
    <div class="tab-content">
        <dietician-dishes-component></dietician-dishes-component>
    </div>
</template>

<script>
import DieticianDishesComponent from '../../../components/diet/dishes/DieticianDishesComponent.vue'
export default {
    name: "DieticianDishesView",
    components: {
        DieticianDishesComponent
    }
}
</script>

<style scoped>
</style>