<template>
    <div class="shared-section-bg" v-if="postSource">
        <div class="d-flex flex-row px-4 pt-3">

            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="50" width="50">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="50" width="50">
            </div>

            <div class="d-flex flex-column align-self-center w-100">
                <div class="text-start d-flex align-items-baseline ms-3">
                    <span id="creator" class="fw-bolder">{{this.postSource.creator.firstName}} {{this.postSource.creator.lastName}}</span>
                    <span id="time" class="ms-3 fw-bolder">| {{this.$func_global.formatDateDateFromNow(this.postSource.addedDate)}}</span>
                </div>
            </div>

        </div>

        <div class="row text-start px-4 pt-2 pb-1">
            <div class="col">
                <p id="post-content" v-html="this.$func_global.convertNewLines(this.postSource.postContent)"></p>
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
    name: "SharedPost",
    props: {
        postSource: Object
    },
    data() {
        return {
            postPictureSrc: "",
            profilePictureSrc: "",
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
                this.$func_global.downloadPhoto(url, token).then(result => this.postPictureSrc = result)
            }
        }
    },
    mounted() {
        this.downloadProfilePicture()
        this.downloadPostPicture()
    },
    watch: {
        postSource: function (){
            this.downloadPostPicture()
        }
    }
}
</script>

<style scoped>
#time {
    color: var(--GREY1);
    font-size: 14px;
}

#post-content {
    text-align: justify;
}

.no-bg:hover {
    background-color: var(--GREY2);
}

.no-bg {
    color: white;
    border-radius: 5px;
}
</style>