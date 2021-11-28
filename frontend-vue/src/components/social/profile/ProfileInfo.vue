<template>
    <div class="section-2-bg">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="!this.$route.params.profileId && this.profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="100" width="100">
                <img v-else-if="this.$route.params.profileId && this.profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="100" width="100">

                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="100" width="100">
            </div>

            <div class="d-flex flex-column align-self-center w-100">

                <div class="text-start d-flex justify-content-between ms-3">
                    <h3>{{this.profileSource.firstName}} {{this.profileSource.lastName}}</h3>

                    <button class="btn-white ms-auto fw-bolder" v-if="isProfileMine">
                        <router-link :to="{ name: 'ProfileEdit' }">
                            Edytuj profil
                        </router-link>
                    </button>
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
        <div class="d-flex flex-row-reverse px-4 px-2">
            <button class="btn-white fw-bolder" v-if="!isProfileMine && !this.isFollowedByMe" @click="addFollow">
                Zaobserwuj
            </button>
            <button class="btn-white fw-bolder" v-else-if="!isProfileMine && this.isFollowedByMe" @click="deleteFollow">
                Przestań obserwować
            </button>
        </div>
        <div class="row text-start px-4 py-3">
            <p v-html="this.$func_global.convertNewLines(this.profileSource.description)"></p>
        </div>
    </div>

</template>

<script>
export default {
    name: "ProfileInfo",
    data() {
        return {
            profilePictureSrc: "",
            isFollowedByMe: false
        }
    },
    props: {
        profileSource: Object
    },
    methods: {
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
        }
    },
    computed: {
        isProfileMine() {
            return !this.$route.params.profileId || this.$route.params.profileId === this.$store.getters.getProfileId
        }
    },
    mounted() {
        if(this.$route.params.profileId) {
            this.downloadProfilePictureByProfileId(this.$route.params.profileId)
            this.checkFollow()
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
</style>