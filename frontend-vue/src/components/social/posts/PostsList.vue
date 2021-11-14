<template>
    <div v-if="postsSource" class="container-fluid p-0">
        <post v-for="post in postsSource" :key="post.postId" :post-source="post" class="mb-4" @edit:post="setModalPost" @get:likes="setLikesList"/>
        <post-edit :edited-post-source="modalPost" />
        <likes-list :likes-source="likesModal" />
    </div>
</template>

<script>
import Post from "@/components/social/posts/Post";
import PostEdit from "@/components/social/posts/PostEdit";
import LikesList from "@/components/social/posts/LikesList";
export default {
    name: "PostsList",
    data() {
        return {
            modalPost: Object,
            likesModal: []
        }
    },
    props: {
        postsSource: Array
    },
    components: {
        Post,
        PostEdit,
        LikesList
    },
    methods: {
        setModalPost(post) {
            this.modalPost = post
        },
        setLikesList(likes) {
            this.likesModal = likes
            console.log('ustawiam')

        }
    }
}
</script>

<style scoped>

</style>