<template>
    <div class="section-bg">
        <div class="row mx-4 pt-4 pb-2">
            <profile-info :profile-source="profile" v-if="profile"/>
        </div>

        <div class="row mx-4 py-2" v-if="!this.$route.params.profileId || this.$route.params.profileId === this.$store.getters.getProfileId">
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
                last: false,
                currentPage: 0
            },
            scrolledToBottom: true,
            loaded: false

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
        getEditedPost(postId) {
            const url = `${this.apiURL}post/${postId}`
            const token = this.$store.getters.getToken;

            return this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                const index = this.posts.findIndex((post => post.postId === postId));
                this.posts[index] = response.data
            })
        },
        getPostsAfterDelete() {
            this.userNavigation.nextPage = 0
            this.loaded = false
            const pages = this.userNavigation.currentPage
            console.log('DELETE PAGES: ', pages+1)
            this.getPosts(this.userNavigation.nextPage, false, pages+1)
            // for (let i = 0; i < pages+1; i++) {
            //     if (i === 0) {
            //         const x = this.getPosts(this.userNavigation.nextPage, false).then(() => {
            //             return 'x'
            //         })
            //         console.log(x)
            //     }
            //     else {
            //         const x = this.getPosts(this.userNavigation.nextPage, true).then(() => {
            //             return 'x'
            //         })
            //         console.log(x)
            //     }
            // }
            this.loaded = true
        },
        getPosts(page, isScroll, pagesAfterDelete) {
            let url;
            if(!this.$route.params.profileId) {
                url = `${this.apiURL}posts/my`
            } else {
                url = `${this.apiURL}posts/${this.$route.params.profileId}`
            }
            const token = this.$store.getters.getToken;
            console.log('PAGE: ', page)
            const myParams = {
                page: page,
                size: this.userNavigation.pageSize
            }
            if(this.userNavigation.last && isScroll)
                return

            console.log('PreGET: ', page, isScroll)

            return this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log('POST GET: ', page, isScroll)

                console.log(response.data)
                if(!this.userNavigation.last && isScroll)
                    this.posts = this.posts.concat(response.data['content'])
                else if (!isScroll) {
                    this.posts = response.data['content']
                    this.userNavigation.nextPage = 0
                }

                this.loaded = true
                this.userNavigation.last = response.data['last']
                this.userNavigation.currentPage = response.data['number']

                if (!this.userNavigation.last) {
                    this.userNavigation.nextPage = this.userNavigation.currentPage+1
                }
                if(pagesAfterDelete>1) {
                    this.getPosts(this.userNavigation.nextPage, true, pagesAfterDelete-1)
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
                    console.log('isLoaded?  ', this.loaded)
                    if (!this.userNavigation.last && this.scrolledToBottom && this.loaded) {
                        this.scrolledToBottom = false
                        this.getPosts(this.userNavigation.nextPage, true, 0).then((response) => {
                            setTimeout(() => {
                                this.scrolledToBottom = true
                            }, 300)
                            console.log(response)
                        }, error => {
                            console.log(error)
                        })
                    }
                }
                console.log(bottomOfWindow, ' ', this.scrolledToBottom)
            }
        }
    },

    created() {
        this.getProfile()
        this.getPosts(this.userNavigation.nextPage, false, 0)
    },
    mounted () {
        this.scroll()
    },
}
</script>

<style scoped>

</style>