<template>
    <div>
        <dish-browser-component :fromNutritionPlan="false"></dish-browser-component>
    </div>
</template>

<script>
import DishBrowserComponent from '../../components/diet/DishBrowserComponent.vue'
export default {
    name: "DietDishBrowserView",
    components: {
        DishBrowserComponent
    }
}
</script>

<style scoped>

</style>