<template>
    <div>
        <measures-component :user-id="this.userId"/>
    </div>
</template>

<script>
import MeasuresComponent from "@/components/telemedic/measures/MeasuresComponent";

export default {
    name: 'UserMeasuresView',
    components: {
        MeasuresComponent
    },
    data() {
        return {
            userId: this.$store.getters.getProfileId,
        }
    },
    methods: {

    },
    created() {

    },
}
</script>

<style scoped>

</style>