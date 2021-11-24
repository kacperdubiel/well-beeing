<template>
    <div>
        <analysis-component :user-id="this.userId" />
    </div>
</template>

<script>
import AnalysisComponent from "@/components/telemedic/analysis/AnalysisComponent";

export default {
    name: 'DoctorUserProfileAnalysisView',
    components: {
        AnalysisComponent,
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