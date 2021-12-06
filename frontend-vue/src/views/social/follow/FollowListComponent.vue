<template>
    <div>
        <div class="row justify-content-center w-100">
            <div class="col-11">
                <div class="row mt-4">

                    <div class="col-6 text-start my-2" v-for="follow in follows" v-bind:key="follow.followId">
                        <div class="row">
                            <div v-if="followType === 'followers'"
                                 class="clickable col-10"
                                 @click="redirectToProfile(follow.follower.id)"
                            >
                                <user-avatar-component :height="60" :width="60"
                                                       :isActive=false
                                                       :profileId="follow.follower.id"
                                />
                                <span class="mx-2">
                                    {{ follow.follower.firstName }} {{ follow.follower.lastName }}
                                </span>
                            </div>
                            <div v-else
                                 class="clickable col-10"

                                 @click="redirectToProfile(follow.followed.id)"
                            >
                                <user-avatar-component :height="60" :width="60"
                                                       :isActive=false
                                                       :profileId="follow.followed.id"
                                />
                                <span class="mx-2">
                                {{ follow.followed.firstName }} {{ follow.followed.lastName }}
                            </span>
                            </div>
                            <div class="align-right col-2 align-self-center">
                                <button class="btn-white m-r-5 btn-hover"
                                        @click="getConversation(getOtherUserId(follow))">
                                    <font-awesome-icon :icon="['fa', 'comments']"/>
                                </button>
                            </div>
                        </div>

                    </div>
                    <div v-if="follows && follows.length === 0 && followType === 'followers'" class="container mt-5">
                        Nikt Cię jeszcze nie obserwuje.
                    </div>
                    <div v-else-if="follows && follows.length === 0 && followType === 'following'" class="container mt-5">
                        Nie obserwujesz jeszcze żadnego użytkownika.
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";

export default {
    name: "FollowListComponent",
    components:{
        UserAvatarComponent
    },
    data() {
        return {

            follows: {},

            navigation: {
                totalElements: 0,
                totalPages: 0,
                isFirst: false,
                isLast: false,
                isEmpty: false,
                currentPage: 0,
                pageSize: 20
            },
            scrolledToBottom: true,
            loaded: true
        }
    },
    props: {
        followType: String,
    },
    methods: {
        redirectToProfile(id) {
            if(id === this.$store.getters.getProfileId)
                this.$router.push({ name: 'MyProfileView'})
            else
                this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        },
        getOtherUserId(follow) {
            return this.followType === 'followers' ? follow.follower.id : follow.followed.id;
        },
        getConversation(profileId) {
            const url = `${this.apiURL}conversations/profile/${profileId}/type/WITH_USER`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then(response => {
                this.openConversation(response.data.id);
            })
                .catch(e => {
                    console.log(e);
                    this.addConversation(profileId);
                })
        },
        addConversation(otherUserId) {
            const url = `${this.apiURL}conversations`
            const token = this.$store.getters.getToken;

            const data = {
                "connectionType": "WITH_USER",
                "firstProfile": {
                    "id": this.userId,
                },
                "secondProfile": {
                    "id": otherUserId,
                },
            }
            this.axios.post(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.openConversation(response.data.id);
            }).catch(error => {
                console.log(error)
            });

        },
        openConversation(conversationId) {
            this.$router.push({ name: 'UserUserConversationView', params: { conversationId: conversationId } });
        },
        getMyFollows (page, isScroll) {
            let url;
            if (this.followType === 'followers')
                url = `${this.apiURL}followers/${this.$store.getters.getProfileId}`
            else if (this.followType === 'following')
                url = `${this.apiURL}following/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            const myParams = {
                page: page,
            }

            if(this.navigation.isLast && isScroll)
                return 'sth'

            if(page === 0)
                this.scrolledToBottom = true

            this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                if(!this.navigation.isLast && isScroll)
                    this.follows = this.follows.concat(response.data['content'])
                else if (!isScroll)
                    this.follows = response.data['content']

                this.loaded = true
                this.navigation.totalElements = response.data['totalElements']
                this.navigation.totalPages = response.data['totalPages']
                this.navigation.isFirst = response.data['first']
                this.navigation.isLast = response.data['last']
                this.navigation.isEmpty = response.data['empty']
                this.navigation.currentPage = response.data['number']

                return 'sth'
            })
        },
        scroll () {
            window.onscroll = () => {
                let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) +
                    window.innerHeight + 5 >= document.documentElement.offsetHeight
                if (bottomOfWindow) {
                    if (!this.navigation.isLast && this.scrolledToBottom && this.loaded) {
                        this.scrolledToBottom = false
                        this.getMyFollows(this.navigation.currentPage+1, true).then((response) => {
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
    mounted() {
        this.getMyFollows(0, false)
        this.scroll()
    },

}
</script>

<style scoped>

</style>