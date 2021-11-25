<template>
    <div class="d-flex flex-row w-100">
        <div class="d-flex flex-column text-start">
            <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="50" width="50">
            <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="50" width="50">
        </div>

        <div class="d-flex flex-column align-self-center">
            <h5 class="ms-2" @click="redirectToProfile(this.profileSource.id)">
                {{this.profileSource.firstName}} {{this.profileSource.lastName}}
            </h5>
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
            const url = `${this.apiURL}profile/export/${this.profileSource.id}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
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