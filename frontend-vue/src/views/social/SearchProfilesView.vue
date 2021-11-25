<template>
    <div>

    </div>
</template>

<script>
export default {
    name: "SearchProfilesView",
    methods: {
        getResults() {
            console.log(this.$store.getters.getSearchProfileResult)
        }
    },
    mounted() {
        this.getResults()
    }
}
</script>

<style scoped>

</style>