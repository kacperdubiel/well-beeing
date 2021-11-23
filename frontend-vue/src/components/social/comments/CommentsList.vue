<template>
    <div v-if="commentsSource" class="container-fluid p-0">
        <comment v-for="com in commentsSource" :key="com.commentId" :comment-source="com" class="mb-2"/>
    </div>
</template>

<script>
import Comment from "@/components/social/comments/Comment";

export default {
    name: "CommentsList",
    props: {
        commentsSource: Array
    },
    components: {
        Comment
    },
}
</script>

<style scoped>

</style>