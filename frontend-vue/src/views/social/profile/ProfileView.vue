<template>
    <div class="section-bg">
        <div class="row mx-4 pt-4 pb-2">
            <profile-info v-if="profile" :profile-source="profile"/>
        </div>

        <div v-if="profile && isSpecialist" class="social-tabs pt-4 px-4">
            <ul class="nav nav-tabs">
                <li class="nav-item clickable" @click="changeView('posts')">
                    <span class="nav-link" v-bind:class="{ active: isPostView }">Posty</span>
                </li>

                <li class="nav-item clickable" @click="changeView('opinions')">
                    <span class="nav-link" v-bind:class="{ active: !isPostView}">Opinie</span>
                </li>
            </ul>
        </div>

        <div v-if="isPostView && isProfileMine" class="row mx-4 py-2">
            <new-post :is-feed="false" v-if="profile" @refresh:posts="getPosts"/>
        </div>

        <div v-if="isPostView && posts && posts.length > 0" class="row mx-4 py-2">
            <posts-list id="posts" :posts-source="posts"/>
        </div>
        <div v-else-if="isPostView  && posts.length === 0" class="container mt-4">
            Brak postów do wyświetlenia :(
        </div>



        <div v-if="!isPostView && profile && (opinions.length > 0 || (myOpinion != null && myOpinion.deleted === false))"
             class="row mx-4 py-2">
            <opinion-average :average="profile.opinionsAverage" :opinions-number="opinionNavigation.totalElements"/>
        </div>

        <div v-if="!isPostView && !isProfileMine && (myOpinion === null || myOpinion.deleted === true) "
             class="row mx-4 py-2">
            <new-opinion v-if="profile"
                         @add:opinion="getMyOpinionToSpecialist(); getProfile(); getOpinions(0, false);"/>
        </div>
        <div v-else-if="!isPostView && !isProfileMine && myOpinion !== null && myOpinion.deleted === false"
             class="row mx-4 py-2">
            <h4 class="text-start">Twoja opinia:</h4>
            <opinion :opinion-source="myOpinion"
                     @delete:opinion="getMyOpinionToSpecialist(); getOpinions(); getProfile();"
                     @update:opinion="getMyOpinionToSpecialist(); getOpinions(); getProfile();"/>
        </div>

        <div v-if="!isPostView && opinions && opinions.length > 0" class="row mx-4 py-2">
            <h4 class="text-start">Opinie innych użytkowników:</h4>
            <opinions-list id="opinions" :opinions-source="opinions"/>
        </div>
        <div v-else-if="!isPostView  && opinions.length === 0" class="container mt-4">
            Brak opinii innych użytkowników do wyświetlenia :(
        </div>

    </div>
</template>

<script>
import ProfileInfo from "@/components/social/profile/ProfileInfo";
import NewPost from "@/components/social/posts/NewPost";
import PostsList from "@/components/social/posts/PostsList";
import OpinionsList from "@/components/social/opinions/OpinionsList";
import NewOpinion from "@/components/social/opinions/NewOpinion";
import OpinionAverage from "@/components/social/opinions/OpinionAverage";
import Opinion from "@/components/social/opinions/Opinion";

export default {
    name: "ProfileView",
    components: {
        ProfileInfo,
        NewPost,
        PostsList,
        OpinionsList,
        NewOpinion,
        OpinionAverage,
        Opinion
    },
    data() {
        return {
            profile: null,
            posts: [],
            opinions: [],
            myOpinion: null,
            postNavigation: {
                nextPage: 0,
                pageSize: 5,
                last: false,
                currentPage: 0
            },
            opinionNavigation: {
                totalElements: 0,
                nextPage: 0,
                pageSize: 5,
                last: false,
                currentPage: 0
            },
            scrolledToBottomPost: true,
            scrolledToBottomOpinion: true,
            loadedPost: false,
            loadedOpinion: false,
            isPostView: true

        }
    },
    methods: {
        getProfile() {
            let url;
            if (!this.$route.params.profileId) {
                url = `${this.apiURL}profile/my`
            } else {
                url = `${this.apiURL}profile/${this.$route.params.profileId}`
            }
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
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
            this.postNavigation.nextPage = 0
            this.loadedPost = false
            const pages = this.postNavigation.currentPage
            this.getPosts(this.postNavigation.nextPage, false, pages + 1)
            this.loadedPost = true
        },
        getPosts(page, isScroll, pagesAfterDelete) {
            let url;
            if (!this.$route.params.profileId) {
                url = `${this.apiURL}posts/my`
            } else {
                url = `${this.apiURL}posts/${this.$route.params.profileId}`
            }
            const token = this.$store.getters.getToken;
            const myParams = {
                page: page,
                size: this.postNavigation.pageSize
            }
            if (this.postNavigation.last && isScroll)
                return

            return this.axios.get(url, {
                params: myParams,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                if (!this.postNavigation.last && isScroll)
                    this.posts = this.posts.concat(response.data['content'])
                else if (!isScroll) {
                    this.posts = response.data['content']
                    this.postNavigation.nextPage = 0
                }

                this.loadedPost = true
                this.postNavigation.last = response.data['last']
                this.postNavigation.currentPage = response.data['number']

                if (!this.postNavigation.last) {
                    this.postNavigation.nextPage = this.postNavigation.currentPage + 1
                }
                if (pagesAfterDelete > 1) {
                    this.getPosts(this.postNavigation.nextPage, true, pagesAfterDelete - 1)
                }
                return 'sth'
            })
        },
        getOpinions(page, isScroll) {
            let url;
            if (!this.$route.params.profileId) {
                url = `${this.apiURL}opinions/${this.$store.getters.getProfileId}`
            } else {
                url = `${this.apiURL}opinions/${this.$route.params.profileId}`
            }
            const token = this.$store.getters.getToken;
            const myParams = {
                page: page,
                size: this.opinionNavigation.pageSize
            }
            if (this.opinionNavigation.last && isScroll)
                return

            return this.axios.get(url, {
                params: myParams,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                let newOpinions = response.data['content'].filter(op => op.giver.id !== this.$store.getters.getProfileId)
                this.opinionNavigation.totalElements = response.data['totalElements']
                if (!this.opinionNavigation.last && isScroll)
                    this.opinions = this.opinions.concat(newOpinions)
                else if (!isScroll) {
                    this.opinions = newOpinions
                    this.opinionNavigation.nextPage = 0
                }

                this.loadedOpinion = true
                this.opinionNavigation.last = response.data['last']
                this.opinionNavigation.currentPage = response.data['number']

                if (!this.opinionNavigation.last) {
                    this.opinionNavigation.nextPage = this.opinionNavigation.currentPage + 1
                }
                return 'sth'
            })
        },
        getMyOpinionToSpecialist() {
            if (this.$route.params.profileId && this.$route.params.profileId !== this.$store.getters.getProfileId) {
                const url = `${this.apiURL}opinions/${this.$route.params.profileId}/my`
                const token = this.$store.getters.getToken;

                return this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                    if (response.data !== "")
                        this.myOpinion = response.data
                })
            }
        },
        scroll() {
            window.onscroll = () => {
                let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) +
                    window.innerHeight + 5 >= document.documentElement.offsetHeight
                if (bottomOfWindow) {
                    if (!this.postNavigation.last && this.scrolledToBottomPost && this.loadedPost && this.isPostView) {
                        this.scrolledToBottomPost = false
                        this.getPosts(this.postNavigation.nextPage, true, 0).then((response) => {
                            setTimeout(() => {
                                this.scrolledToBottomPost = true
                            }, 300)
                            console.log(response)
                        }, error => {
                            console.log(error)
                        })
                    } else if (!this.opinionNavigation.last && this.scrolledToBottomOpinion && this.loadedOpinion && !this.isPostView) {
                        this.scrolledToBottomOpinion = false
                        this.getOpinions(this.postNavigation.nextPage, true).then((response) => {
                            setTimeout(() => {
                                this.scrolledToBottomPost = true
                            }, 300)
                            console.log(response)
                        }, error => {
                            console.log(error)
                        })
                    }
                }
            }
        },
        changeView(type) {
            if (type === 'posts')
                this.isPostView = true
            else if (type === 'opinions')
                this.isPostView = false
        }
    },

    created() {
        this.getProfile()
        this.getPosts(this.postNavigation.nextPage, false, 0).then(() => {
            if (this.isSpecialist) {
                this.getOpinions(this.opinionNavigation.nextPage, false)
                this.getMyOpinionToSpecialist()
            }
        })
    },
    mounted() {
        this.scroll()
    },
    computed: {
        isSpecialist() {
            return this.profile.doctorProfile != null || this.profile.dieticianProfile != null || this.profile.trainerProfile != null
        },
        isProfileMine() {
            return !this.$route.params.profileId || this.$route.params.profileId === this.$store.getters.getProfileId
        }
    },
}
</script>

<style scoped>

</style>
