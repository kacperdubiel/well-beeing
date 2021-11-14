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

                    <button class="btn-white ms-auto fw-bolder" v-if="!this.$route.params.profileId">
                        <router-link :to="{ name: 'ProfileEdit' }">
                            Edytuj profil
                        </router-link>
                    </button>
                </div>

                <div class="text-start d-flex flex-row ms-2">
                    <div class="p-2 mx-2 tag-sport fw-bolder" v-if="this.profileSource.esportTag !== 'NONE'">
                        {{ this.$func_global.mapSportTag(this.profileSource.esportTag )}}
                    </div>
                    <div class="p-2 mx-2 tag-nutrition fw-bolder" v-if="this.profileSource.enutritionTag !== 'NONE'">
                        {{ this.$func_global.mapNutritionTag(this.profileSource.enutritionTag) }}
                    </div>
                </div>

            </div>
        </div>
        <div class="row text-start px-4 py-3">
            <p>
                {{this.profileSource.description}}
            </p>
        </div>
    </div>

</template>

<script>
export default {
    name: "ProfileInfo",
    data() {
        return {
            profilePictureSrc: ""
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
        }
    },
    mounted() {
        if(this.$route.params.profileId)
            this.downloadProfilePictureByProfileId(this.$route.params.profileId)
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