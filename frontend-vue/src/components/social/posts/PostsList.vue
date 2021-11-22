<template>
    <div v-if="postsSource" class="container-fluid p-0">
        <post v-for="post in postsSource" :key="post.postId" :post-source="post" class="mb-4" @edit:post="setModalPost" @share:post="setModalPostId" @get:likes="setLikesList"/>
        <post-edit :edited-post-source="modalPost" />
        <post-share :shared-post-id="modalPostId" :key="modalPostId"/>
        <likes-list :likes-source="likesModal" />
    </div>
</template>

<script>
import Post from "@/components/social/posts/Post";
import PostEdit from "@/components/social/posts/PostEdit";
import PostShare from "@/components/social/posts/PostShare";
import LikesList from "@/components/social/posts/LikesList";
export default {
    name: "PostsList",
    data() {
        return {
            modalPost: Object,
            modalPostId: 0,
            likesModal: []
        }
    },
    props: {
        postsSource: Array
    },
    components: {
        Post,
        PostEdit,
        LikesList,
        PostShare
    },
    methods: {
        setModalPost(post) {
            this.modalPost = Object.assign({},post)
        },
        setModalPostId(postId) {
            this.modalPostId = postId
        },
        setLikesList(likes) {
            this.likesModal = likes
        }
    }
}
</script>

<style scoped>

</style>