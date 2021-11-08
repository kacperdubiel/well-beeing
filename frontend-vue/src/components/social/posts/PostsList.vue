<template>
    <div v-if="postsSource" class="container-fluid p-0">
        <post v-for="post in postsSource" :key="post.postId" :post-source="post" class="mb-4"/>
    </div>
</template>

<script>
import Post from "@/components/social/posts/Post";
export default {
    name: "PostsList",
    props: {
        postsSource: Array
    },
    components: {
        Post
    },
    methods: {

    }
}
</script>

<style scoped>

</style>