<template>
    <div>
        <follow-list-component follow-type="followers"/>
    </div>
</template>

<script>
import FollowListComponent from "@/views/social/follow/FollowListComponent";

export default {
    name: "MyFollowersView",
    components: {
        FollowListComponent
    }
}
</script>

<style scoped>

</style>