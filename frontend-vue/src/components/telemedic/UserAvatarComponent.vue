<template>
    <span class="position-relative">
        <img v-if="this.profileImage" :src="this.profileImage" alt="Profile picture" class="profile-picture"
             :height="height" :width="width">
        <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture"
             :height="height" :width="width">
        <a v-if="isActive" class="circle position-absolute end-0 bottom-100"></a>
    </span>
</template>

<script>
export default {
    name: "UserAvatarComponent",
    props: {
        profileId: String,
        height: Number,
        width: Number,
        isActive: Boolean
    },
    data() {
        return {
            profileImage: null
        }
    },
    methods: {
        downloadProfilePicture() {
            const url = `${this.apiURL}profile/export/${this.profileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token)
                .then(result => {
                    this.profileImage = result;
                })
        }
    },
    mounted() {
        this.downloadProfilePicture();
    }
}
</script>

<style scoped>

.profile-picture {
    border: 2px solid white;
}
.circle {
    bottom: 80%!important;
}
</style>
