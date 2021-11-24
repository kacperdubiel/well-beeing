<template>
    <div>
        <analysis-component :user-id="this.userId" />
    </div>
</template>

<script>
import AnalysisComponent from "@/components/telemedic/analysis/AnalysisComponent";

export default {
    name: 'AnalysisView',
    components: {
        AnalysisComponent,
    },
    data() {
        return {
            userId: this.$store.getters.getProfileId,
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