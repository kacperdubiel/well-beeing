<template>
    <div class="d-flex flex-row w-100">
        <div class="d-flex flex-column text-start">
            <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="50" width="50">
            <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="50" width="50">
        </div>

        <div class="d-flex flex-column align-self-center w-100">
            <h5 class="ms-2">
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
    },
    mounted() {
        this.downloadProfilePicture()
    },
}
</script>

<style scoped>

</style>