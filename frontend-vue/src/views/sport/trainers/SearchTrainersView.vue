<template>
<div>
    <section>
        <search-specialist-component class="m-3" connection-type="WITH_TRAINER"/>
    </section>
</div>
</template>

<script>
import SearchSpecialistComponent from "@/components/telemedic/user-connections/SearchSpecialistComponent";

export default {
    name: "SearchTrainersView",
    components: {
        SearchSpecialistComponent
    },
    data() {
        return {

        }
    },
    methods: {

    },
}
</script>

<style scoped>

</style>
