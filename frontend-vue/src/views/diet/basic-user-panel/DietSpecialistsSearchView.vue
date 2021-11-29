<template>
    <div class="tab-content">
         <search-specialist-component connection-type="WITH_DIETICIAN"/>
    </div>
</template>

<script>
import SearchSpecialistComponent from "@/components/telemedic/user-connections/SearchSpecialistComponent.vue";
export default {
    name: "DietSpecialistsSearchView",
    components: {
        SearchSpecialistComponent
    }
}
</script>

<style scoped>

</style>