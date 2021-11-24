<template>
    <div>
        <measures-component :user-id="this.userId" />
    </div>
</template>

<script>
import MeasuresComponent from "@/components/telemedic/measures/MeasuresComponent";

export default {
    name: 'DoctorUserProfileMeasuresView',
    components: {
        MeasuresComponent
    },
    data() {
        return {
            userId: this.$route.params.userId,
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