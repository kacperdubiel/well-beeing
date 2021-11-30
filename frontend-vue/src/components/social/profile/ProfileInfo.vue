<template>
    <div class="section-2-bg">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="!this.$route.params.profileId && this.profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="120" width="120">
                <img v-else-if="this.$route.params.profileId && this.profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="120" width="120">

                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="120" width="120">
            </div>

            <div class="d-flex flex-column align-self-center w-100">

                <div class="text-start d-flex ms-3">
                    <h3 class="align-self-center mb-0">{{this.profileSource.firstName}} {{this.profileSource.lastName}}</h3>
                    <font-awesome-icon :icon="['fa', 'certificate']" size="1x" v-if="isSpecialist" id="specialist-badge" class="ms-2 align-self-center"/>

                    <button class="btn-white ms-auto fw-bolder" v-if="isProfileMine">
                        <router-link :to="{ name: 'ProfileEdit' }">
                            Edytuj profil
                        </router-link>
                    </button>
                </div>

                <div class="row text-start mt-2 ms-2">
                    <div class="d-flex flex-row align-items-baseline" v-if="this.profileSource.doctorProfile != null">
                        <span class="fw-bolder role">Lekarz</span>
                        <span
                            v-for="spec in this.profileSource.doctorProfile.specializations" :key=spec.id
                            class="ms-2 spec fw-bolder"
                        >
                            ∙ {{spec.name}}
                        </span>
                    </div>

                    <div class="d-flex flex-row" v-if="this.profileSource.dieticianProfile != null">
                        <span class="fw-bolder role">Dietetyk</span>
                    </div>

                    <div class="d-flex flex-row" v-if="this.profileSource.trainerProfile != null">
                        <span class="fw-bolder role">Trener</span>
                    </div>
                </div>


                <div class="text-start d-flex flex-row ms-2 mt-2">
                    <div class="p-2 mx-2 tag-sport fw-bolder" v-if="this.profileSource.esportTag !== 'NONE' && this.profileSource.esportTag != null">
                        {{ this.$func_global.mapSportTag(this.profileSource.esportTag )}}
                    </div>
                    <div class="p-2 mx-2 tag-nutrition fw-bolder" v-if="this.profileSource.enutritionTag !== 'NONE' && this.profileSource.enutritionTag != null">
                        {{ this.$func_global.mapNutritionTag(this.profileSource.enutritionTag) }}
                    </div>

                </div>

            </div>
        </div>
        <div class="d-flex flex-row-reverse px-4 mt-2">
            <button class="btn-white fw-bolder ms-2" v-if="!isProfileMine && isSpecialist  && !this.isFollowedByMe" @click="addFollow">
                Zaobserwuj
            </button>
            <button class="btn-white fw-bolder ms-2" v-else-if="!isProfileMine && isSpecialist && this.isFollowedByMe" @click="deleteFollow">
                Przestań obserwować
            </button>


            <div  class="dropdown btn-white fw-bolder" v-if="!isProfileMine && isFriend">
                <a class="dropdown-toggle ms-2" href="#" role="button" id="dropdown-is-friend" data-bs-toggle="dropdown" aria-expanded="false">
                    Znajomi
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdown-is-friend">
                    <li><a @click="setDeleteProps(0)" class="dropdown-item"
                           data-bs-toggle="modal" data-bs-target="#deleteFriendConnectionModal">Usuń ze znajomych</a></li>
                </ul>
            </div>

            <div  class="dropdown btn-white fw-bolder" v-else-if="!isProfileMine && isInvitedByMe">
                <a class="dropdown-toggle ms-2" href="#" role="button" id="dropdown-inv-sent" data-bs-toggle="dropdown" aria-expanded="false">
                    Wysłano zaproszenie do znajomych
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdown-inv-sent">
                    <li><a @click="setDeleteProps(1)" class="dropdown-item"
                           data-bs-toggle="modal" data-bs-target="#deleteFriendConnectionModal">Anuluj zaproszenie</a></li>
                </ul>
            </div>

            <div  class="dropdown btn-white fw-bolder" v-else-if="!isProfileMine && hasInvitedMe">
                <a class="dropdown-toggle ms-2" href="#" role="button" id="dropdown-inv-received" data-bs-toggle="dropdown" aria-expanded="false">
                    Odpowiedz na  zaproszenie do znajomych
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdown-inv-received">
                    <li><a class="dropdown-item"
                           data-bs-toggle="modal" data-bs-target="#acceptFriendModal">Potwierdź</a></li>
                    <li><a @click="setDeleteProps(2)" class="dropdown-item"
                           data-bs-toggle="modal" data-bs-target="#deleteFriendConnectionModal">Odrzuć</a></li>
                </ul>
            </div>

            <button class="btn-white fw-bolder" v-else-if="!isProfileMine && isNoConnection" @click="inviteFriend">
                Zaproś do znajomych
            </button>

            <delete-connection-friend-modal :delete-type="deleteType" @delete:invitation="deleteInvitation"/>
            <accept-friend-modal @accept:invitation="acceptInvitation"/>
        </div>
        <div class="row text-start px-4 py-3">
            <p v-html="this.$func_global.convertNewLines(this.profileSource.description)"></p>
        </div>
    </div>

</template>

<script>
import DeleteConnectionFriendModal from "@/components/social/profile/DeleteConnectionFriendModal";
import AcceptFriendModal from "@/components/social/profile/AcceptFriendModal";

export default {
    name: "ProfileInfo",
    components: {
        DeleteConnectionFriendModal,
        AcceptFriendModal
    },
    data() {
        return {
            profilePictureSrc: "",
            isFollowedByMe: false,
            connectionsList: [],
            deleteType: "",
            connectionIndexOnList: Number
        }
    },
    props: {
        profileSource: Object
    },
    methods: {

        setDeleteProps(index) {
            this.connectionIndexOnList = index
            if(index === 0)
                this.deleteType = 'friend'
            else if (index === 1)
                this.deleteType = 'invitationSent'
            else if (index === 2) {
                this.deleteType = 'invitationReceived'
            }
        },

        downloadMyProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        downloadProfilePictureByProfileId (profileId) {
            const url = `${this.apiURL}profile/export/${profileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        addFollow() {
            const url = `${this.apiURL}follow/${this.$route.params.profileId}`
            const token = this.$store.getters.getToken;
            this.axios.post(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.checkFollow()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        deleteFollow() {
            const url = `${this.apiURL}follow/${this.$route.params.profileId}`
            const token = this.$store.getters.getToken;
            this.axios.delete(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.checkFollow()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        checkFollow() {
            const url = `${this.apiURL}follow/${this.$route.params.profileId}`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.isFollowedByMe = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        checkFriends() {
            const url = `${this.apiURL}profile-connections/${this.$route.params.profileId}/check`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.connectionsList = response.data
            }).catch(error => {
                console.log(error.response.status)
            });
        },

        deleteInvitation() {
            const url = `${this.apiURL}profile-connections/${this.connectionsList[this.connectionIndexOnList].id}`
            const token = this.$store.getters.getToken;

            this.axios.delete(url,{headers: {Authorization: `Bearer ${token}`}}).then(() => {
                this.checkFriends()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        acceptInvitation(){
            const url = `${this.apiURL}profile-connections/${this.connectionsList[2].id}/mark-as-accepted`
            const token = this.$store.getters.getToken;

            this.axios.put(url, null,{headers: {Authorization: `Bearer ${token}`}}).then(() => {
                this.checkFriends()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        inviteFriend(){
            const url = `${this.apiURL}profile-connections`
            const token = this.$store.getters.getToken;

            const data = {
                "connectionType": "WITH_USER",
                "connectedWith": {
                    "id": this.$route.params.profileId,
                },
            }

            this.axios.post(url, data,{headers: {Authorization: `Bearer ${token}`}}).then(() => {
                this.checkFriends()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
    },
    computed: {
        isProfileMine() {
            return !this.$route.params.profileId || this.$route.params.profileId === this.$store.getters.getProfileId
        },
        isSpecialist() {
            return this.profileSource.doctorProfile != null || this.profileSource.dieticianProfile != null || this.profileSource.trainerProfile != null
        },
        isFriend() {
            return this.connectionsList[0] != null && this.connectionsList[1] == null && this.connectionsList[2] == null
        },
        isInvitedByMe() {
            return this.connectionsList[0] == null && this.connectionsList[1] != null && this.connectionsList[2] == null
        },
        hasInvitedMe() {
            return this.connectionsList[0] == null && this.connectionsList[1] == null && this.connectionsList[2] != null
        },
        isNoConnection() {
            return this.connectionsList[0] == null && this.connectionsList[1] == null && this.connectionsList[2] == null
        }
    },
    mounted() {
        if(this.$route.params.profileId) {
            this.downloadProfilePictureByProfileId(this.$route.params.profileId)
            this.checkFollow()
            this.checkFriends()
        }
        else
            this.downloadMyProfilePicture()
    }
}
</script>

<style scoped>


div[class*="tag-"] {
    border-radius: 20px;
}

.tag-nutrition {
    background-color: var(--DIET);
}

.tag-sport {
    background-color: var(--SPORT);
}

a {
    color: var(--GREY3);
    text-decoration: none;
}

#specialist-badge {
    color: var(--DARK-YELLOW);
}

.role {
    font-size: 18px;
}

.spec {
    color: var(--GREY1);
}
</style>