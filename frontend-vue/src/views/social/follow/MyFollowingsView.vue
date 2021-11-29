<template>
    <div>
        <follow-list-component follow-type="following"/>
    </div>
</template>

<script>
import FollowListComponent from "@/views/social/follow/FollowListComponent";

export default {
    name: "MyFollowingsView",
    components: {
        FollowListComponent
    }
}
</script>

<style scoped>

</style>