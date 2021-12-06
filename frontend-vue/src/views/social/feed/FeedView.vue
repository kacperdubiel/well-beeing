<template>
    <div class="section-bg pt-3">
        <div class="row mx-4 py-2">
            <new-post :is-feed="true" @refresh:posts="getPostsFeedWithNewPost"/>
        </div>

        <div class="row mx-4 py-2">
            <posts-list v-if="posts" id="posts" :posts-source="posts"/>
        </div>


    </div>

</template>

<script>
import NewPost from "@/components/social/posts/NewPost";
import PostsList from "@/components/social/posts/PostsList";

export default {
    name: "FeedView",
    components: {
        NewPost,
        PostsList
    },
    data() {
        return {
            posts: [],
            postNavigation: {
                nextPage: 0,
                pageSize: 3,
                last: false,
                currentPage: 0
            },
            loadingDate: new Date(),
            scrolledToBottom: true,
            loaded: false
        }
    },
    methods: {
        getPostsFeedWithNewPost(page, isScroll, postId) {
            this.getPostsFeed(page, isScroll).then(() => {
                const url = `${this.apiURL}post/${postId}`
                const token = this.$store.getters.getToken;
                this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                    this.posts.unshift(response.data)
                }).catch(error => {
                    console.log(error.response.status)
                });
            })



        },
        getPostsFeed(page, isScroll) {
            let url = `${this.apiURL}posts/feed`
            const token = this.$store.getters.getToken;
            const myParams = {
                page: page,
                size: this.postNavigation.pageSize,
                requestDate: this.$func_global.formatDateTimeFeed(this.loadingDate),
                positioningType: 'points'
            }

            if (this.postNavigation.last && isScroll)
                return

            return this.axios.get(url, {
                params: myParams,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                console.log(response.data)
                if (!this.postNavigation.last && isScroll)
                    this.posts = this.posts.concat(response.data['content'])
                else if (!isScroll) {
                    this.posts = response.data['content']
                    this.postNavigation.nextPage = 0
                }

                this.loaded = true
                this.postNavigation.last = response.data['last']
                this.postNavigation.currentPage = response.data['number']

                if (!this.postNavigation.last) {
                    this.postNavigation.nextPage = this.postNavigation.currentPage + 1
                }
                return 'sth'
            })
        },
        scroll() {
            window.onscroll = () => {
                let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) +
                    window.innerHeight + 5 >= document.documentElement.offsetHeight

                if (bottomOfWindow) {
                    if (!this.postNavigation.last && this.scrolledToBottom && this.loaded) {
                        this.scrolledToBottom = false
                        this.getPostsFeed(this.postNavigation.nextPage, true).then((response) => {
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
        },
    },
    created() {
      this.getPostsFeed(0, false)
    },
    mounted() {
        this.scroll();
    }

}
</script>

<style scoped>

</style>