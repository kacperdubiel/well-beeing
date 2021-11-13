<template>
    <div>
        <img v-if="this.profileImage" :src="this.profileImage" alt="Profile picture" class="profile-picture"
             :height="height" :width="width">
        <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture"
             :height="height" :width="width">
    </div>
</template>

<script>
export default {
    name: "UserAvatarComponent",
    props: {
        profileId: String,
        height: Number,
        width: Number
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

</style>