<template>
    <div class="section-bg">
        <div class="row mx-4 pt-4 pb-2">
            <profile-info :profile-source="profile" v-if="profile"/>
        </div>

        <div class="row mx-4 py-2">
            <new-post v-if="profile" @refresh:posts="getMyPosts"/>
        </div>

        <div class="row mx-4 py-2">
            <posts-list v-if="posts" :posts-source="posts"/>
        </div>

    </div>
</template>

<script>
import ProfileInfo from "@/components/social/profile/ProfileInfo";
import NewPost from "@/components/social/posts/NewPost";
import PostsList from "@/components/social/posts/PostsList";
export default {
    name: "ProfileView",
    components: {
        ProfileInfo,
        NewPost,
        PostsList
    },
    data () {
        return {
            profile: null,
            posts: []
        }
    },
    methods: {
        getMyProfile() {
            const url = `${this.apiURL}profile/my`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.profile = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        getProfileById(id) {
            const url = `${this.apiURL}profile/${id}`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.profile = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        getMyPosts() {
            const url = `${this.apiURL}post/my`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.posts = response.data['content']
            })
        }
    },
    mounted() {
        if (this.$route.params.profileId) {
            console.log('id')
            this.getProfileById()

        }
        else {
            console.log('mój')
            this.getMyProfile()
            this.getMyPosts()
        }
    }
}
</script>

<style scoped>

</style>