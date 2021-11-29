<template>

    <div>
        <div class="row justify-content-center w-100">
            <div class="col-11">
                <div class="row my-3">
                    <div class="col-md-5 col-sm-6 text-start align-self-end">
                        <input
                            type="text"
                            placeholder="Wyszukaj..."
                            id="search-input"
                            class="w-100"
                            v-model="searchName"
                            @keydown.enter="getMyFriends(0, false)"
                        />
                    </div>


                    <div class="col-md-3 col-sm-6 text-start">
                        <label for="sport-tag-select" class="form-label">Tag sportowy:</label>
                        <select
                            v-model="filters.sportTagFilter"
                            class="p-2 w-100"
                            id="sport-tag-select"
                            @change="getMyFriends(0, false)"
                        >
                            <option v-for="sportTag in filters.allSportTagFilters" :key="sportTag.label" :value="sportTag.value">{{ sportTag.label }}</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-sm-6 text-start">
                        <label for="nutrition-tag-select" class="form-label">Tag żywieniowy:</label>
                        <select
                            v-model="filters.nutritionTagFilter"
                            class="p-2 w-100"
                            id="nutrition-tag-select"
                            @change="getMyFriends(0, false)"
                        >
                            <option v-for="nutTag in filters.allNutritionTagFilters" :key="nutTag.label" :value="nutTag.value">{{ nutTag.label }}</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="row text-start" v-for="friendCon in friends" v-bind:key="friendCon.id">
                            <div v-if="friendCon.profile.id !== userId"
                                 class="clickable col"
                                 @click="redirectToProfile(friendCon.profile.id)"
                            >
                                <user-avatar-component :height="60"
                                                       :isActive="this.$func_global.getIsActive5minutes(friendCon.profile.lastRequestTime)"
                                                       :profileId="friendCon.profile.id" :width="60"
                                />
                                <span class="mx-2">
                                        {{ friendCon.profile.firstName }} {{ friendCon.profile.lastName }}
                                    </span>
                            </div>
                            <div v-else
                                 class="clickable col"

                                 @click="redirectToProfile(friendCon.connectedWith.id)"
                            >
                                <user-avatar-component :height="60"
                                                       :isActive="this.$func_global.getIsActive5minutes(friendCon.connectedWith.lastRequestTime)"
                                                       :profileId="friendCon.connectedWith.id" :width="60"
                                />
                                <span class="mx-2">
                                    {{ friendCon.connectedWith.firstName }} {{ friendCon.connectedWith.lastName }}
                                </span>
                            </div>
                            <div class="align-right col">
                                <button class="btn-white m-r-5 btn-hover"
                                        @click="getConversation(getOtherUserId(friendCon))">
                                    <font-awesome-icon :icon="['fa', 'comments']"/>
                                </button>
                            </div>
                        </div>
                        <div v-if="friends && friends.length === 0" class="container mt-2">
                            Brak wpisów.
                        </div>
                    </div>
                </div>


            </div>

        </div>

    </div>

</template>

<script>
import UserAvatarComponent from "@/components/telemedic/UserAvatarComponent";

export default {
    name: "MyFriendsView",
    components:{UserAvatarComponent},
    data(){
        return {
            componentError: null,
            friends: {},
            userId: this.$store.getters.getProfileId,
            selectedUserConnection: {},

            searchName: "",
            filters: {
                allNutritionTagFilters: [
                    {label:'-', value:''},
                    {label:'Dieta wegetariańska', value:'VEGETARIAN'},
                    {label:'Dieta wegańska', value:'VEGAN'},
                    {label:'Dieta bezglutenowa', value:'GLUTEN_FREE'}
                ],
                nutritionTagFilter: '',
                allSportTagFilters: [
                    {label:'-', value:''},
                    {label:'Trening siłowy', value:'WEIGHT_TRAINING'},
                    {label:'Kardio', value:'CARDIO'},
                    {label:'Pilates', value:'PILATES'},
                    {label:'Joga', value:'YOGA'}
                ],
                sportTagFilter: '',
            },
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
    methods: {
        redirectToProfile(id) {
            if(id === this.$store.getters.getProfileId)
                this.$router.push({ name: 'MyProfileView'})
            else
                this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        },
        getOtherUserId(connection) {
            return connection.profile.id !== this.userId ? connection.profile.id : connection.connectedWith.id;
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
            this.$emit('open-conversation', conversationId);
        },
        getMyFriends (page, isScroll) {
            const url = `${this.apiURL}friends`
            const token = this.$store.getters.getToken;
            const myParams = {
                userId: this.$store.getters.getProfileId,
                name: this.searchName,
                page: page,
                eSportTag: this.filters.sportTagFilter,
                eNutritionTag: this.filters.nutritionTagFilter
            }

            if(this.navigation.isLast && isScroll)
                return 'sth'

            if(page === 0)
                this.scrolledToBottom = true

            this.axios.get(url, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                if(!this.navigation.isLast && isScroll)
                    this.friends = this.friends.concat(response.data['content'])
                else if (!isScroll)
                    this.friends = response.data['content']

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
                        this.getMyFriends(this.navigation.currentPage+1, true).then((response) => {
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
        this.getMyFriends(0, false)
        this.scroll()
    },
}
</script>

<style scoped>
.m-r-5 {
    margin-right: 5px
}

.btn-hover:hover {
    color: var(--GREY1);
}

.connections-table tbody tr:hover {
    background-color: var(--TELEMEDIC);
}

.connections-table-sport tbody tr:hover {
    background-color: var(--SPORT);
}

.connections-table-diet tbody tr:hover {
    background-color: var(--DIET);
}


a {
    color: white;
    text-decoration: none;
}
</style>