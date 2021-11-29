<template>
    <div class="d-flex flex-row w-100">
        <div class="d-flex flex-column text-start">
            <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="50" width="50">
            <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="50" width="50">

        </div>

        <div class="d-flex flex-column align-self-center w-100">
            <div class="text-start d-flex ms-3">
                <h5 class="m-0" @click="redirectToProfile(this.profileSource.id)">
                    {{this.profileSource.firstName}} {{this.profileSource.lastName}}
                </h5>
                <button class="btn-white ms-auto mx-1">
                    <font-awesome-icon :icon="['fa', 'comments']" size="1x"/>
                </button>
            </div>

        </div>

    </div>
</template>

<script>
export default {
    name: "ProfileOnListItem",
    data() {
        return {
            profilePictureSrc: "",
        }
    },
    props: {
        profileSource: Object
    },
    methods: {
        downloadProfilePicture () {
            if(this.profileSource.profileImgPath != null) {
                const url = `${this.apiURL}profile/export/${this.profileSource.id}`
                const token = this.$store.getters.getToken;
                this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
            }
        },
        redirectToProfile(id) {
            let modalToClose = document.getElementById('likes-modal-close')
            if(modalToClose)
                modalToClose.click();
            if(id === this.$store.getters.getProfileId)
                this.$router.push({ name: 'MyProfileView'})
            else
                this.$router.push({ name: 'ProfileView', params: {profileId: id} })
        }
    },
    mounted() {
        this.downloadProfilePicture()
    },
}
</script>

<style scoped>
h5:hover {
    text-decoration: underline;
}
</style>