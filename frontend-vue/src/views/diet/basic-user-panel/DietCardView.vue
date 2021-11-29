<template>
    <div class="tab-content">
        <profile-card-component></profile-card-component>
    </div>
</template>

<script>
import ProfileCardComponent from '../../../components/diet/profile-card/ProfileCardComponent.vue'
export default {
    name: "DietCardView",
    components: {
        ProfileCardComponent
    }
}
</script>

<style scoped>

</style>