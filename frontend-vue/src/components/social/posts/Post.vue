<template>
    <div class="section-2-bg" v-if="postSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>

            <div class="d-flex flex-column align-self-center w-100">

                <div class="text-start d-flex align-items-baseline ms-3">
                    <h5>{{this.postSource.creator.firstName}} {{this.postSource.creator.lastName}}</h5>
                    <h6 class="ms-3">| {{this.$func_global.formatDateDateFromNow(this.postSource.addedDate)}}</h6>
                </div>
            </div>
        </div>
        <div class="row text-start px-4 pt-2 pb-1">
            <div class="col">
                <p id="post-content">
                    {{this.postSource.postContent}}
                </p>
            </div>
        </div>
        <div class="row text-start pb-3" v-if="this.postSource.postImgPath">
            <div class="col">
                <img :src="postPictureSrc" alt="Post picture"  id="post-picture" class="w-100">
            </div>
        </div>

    </div>
</template>

<script>
export default {
    name: "Post",
    props: {
        postSource: Object
    },
    data() {
        return {
            profilePictureSrc: "",
            postPictureSrc: ""
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.postSource.creator.id}`
            const token = this.$store.getters.getToken;
            console.log('profile')
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        downloadPostPicture () {
            if (this.postSource.postImgPath) {
                const url = `${this.apiURL}post/export/${this.postSource.postId}`
                const token = this.$store.getters.getToken;
                console.log('post')
                this.$func_global.downloadPhoto(url, token).then(result => this.postPictureSrc = result)
            }

        }
    },
    mounted() {
        this.downloadProfilePicture()
        this.downloadPostPicture()
    }
}
</script>

<style scoped>
h6 {
    color: var(--GREY1);
}

#post-content {
    text-align: justify;
}
</style>