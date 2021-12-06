<template>
    <div v-if="opinionsSource" class="container-fluid p-0">
        <opinion v-for="opinion in opinionsSource" :key="opinion.opinionId" :opinion-source="opinion" class="mb-4"/>
    </div>
</template>

<script>
import Opinion from "@/components/social/opinions/Opinion";

export default {
    name: "OpinionsList",
    components: {
        Opinion
    },
    props: {
        opinionsSource: Array
    },
}
</script>

<style scoped>

</style>