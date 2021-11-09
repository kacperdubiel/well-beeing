<template>
    <div v-if="postsSource" class="container-fluid p-0">
        <post v-for="post in postsSource" :key="post.postId" :post-source="post" class="mb-4" @edit:post="setModalPost"/>
        <post-edit :edited-post-source="modalPost" />
    </div>
</template>

<script>
import Post from "@/components/social/posts/Post";
import PostEdit from "@/components/social/posts/PostEdit";
export default {
    name: "PostsList",
    data() {
        return {
            modalPost: Object
        }
    },
    props: {
        postsSource: Array
    },
    components: {
        Post,
        PostEdit
    },
    methods: {
        setModalPost(post) {
            this.modalPost = post
        }
    }
}
</script>

<style scoped>

</style>