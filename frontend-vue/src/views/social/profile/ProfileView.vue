<template>
    <div class="section-bg">
        <div class="row mx-4 pt-4 pb-2">
            <profile-info :profile-source="profile" v-if="profile"/>
        </div>

        <div class="row mx-4 py-2" v-if="!this.$route.params.profileId">
            <new-post v-if="profile" @refresh:posts="getPosts"/>
        </div>

        <div class="row mx-4 py-2">
            <posts-list v-if="posts" :posts-source="posts" id="posts"/>
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
            posts: [],
            userNavigation: {
                nextPage: 0,
                pageSize: 5,
                last: false
            },
            scrolledToBottom: true,
        }
    },
    methods: {
        getProfile() {
            let url;
            if(!this.$route.params.profileId) {
                url = `${this.apiURL}profile/my`
            } else {
                url = `${this.apiURL}profile/${this.$route.params.profileId}`
            }
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.profile = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        getPosts() {
            let url;
            if(!this.$route.params.profileId) {
                url = `${this.apiURL}posts/my`
            } else {
                url = `${this.apiURL}posts/${this.$route.params.profileId}`
            }
            const token = this.$store.getters.getToken;

            const myParams = {
                page: this.userNavigation.nextPage,
                size: this.userNavigation.pageSize
            }
            // console.log("get posts pre, last: ", this.userNavigation.last)

            return this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                // console.log("get posts post, last: ", this.userNavigation.last)
                // console.log("set last ", this.userNavigation.last)
                if(!this.userNavigation.last)
                    this.posts = this.posts.concat(response.data['content'])
                this.userNavigation.last = response.data['last']

                if (!this.userNavigation.last) {
                    this.userNavigation.nextPage += 1
                }
                return 'sth'

            })
        },
        scroll () {
            window.onscroll = () => {
                let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) +
                    window.innerHeight + 5 >= document.documentElement.offsetHeight
                // console.log(Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop), ' + ')
                // console.log(window.innerHeight, ' = ')
                // console.log(document.documentElement.offsetHeight)
                if (bottomOfWindow) {
                    if (!this.userNavigation.last && this.scrolledToBottom) {
                        this.scrolledToBottom = false
                        this.getPosts().then((response) => {
                            setTimeout(() => {
                                this.scrolledToBottom = true
                            }, 300)
                            console.log(response)
                        }, error => {
                            console.log(error)
                        })
                    }
                }
            }
        }
    },

    created() {
        this.getProfile()
        this.getPosts()
    },
    mounted () {
        this.scroll()
    },
}
</script>

<style scoped>

</style>