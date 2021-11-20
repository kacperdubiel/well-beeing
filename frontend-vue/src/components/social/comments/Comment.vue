<template>
    <div v-if="commentSource">
        <div class="d-flex flex-row px-4 py-1">
            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="40" width="40">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="40" width="40">
            </div>
            <div class="d-flex flex-column align-self-center ms-1">
                <div class="row ms-1 mb-1">
                    <div class="text-start d-flex align-items-baseline">
                        <span class="fw-bolder me-1">{{this.commentSource.commenter.firstName}} {{this.commentSource.commenter.lastName}}</span>
                        <span id="time">∙ {{this.$func_global.formatDateDateFromNow(this.commentSource.addedDate)}}</span>
                    </div>

                </div>
                <span class="comment">
                    {{this.commentSource.comContent}}
                </span>

            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Comment",
    props: {
        commentSource: Object
    },
    data() {
        return {
            profilePictureSrc: "",
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.commentSource.commenter.id}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
    },
    mounted() {
        this.downloadProfilePicture()
    }
}
</script>

<style scoped>
.comment {
    background-color: var(--GREY2);
    border: none;
    border-radius: 30px;
    padding: 1rem 1rem;
    text-align: left;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    color: white;
}

#time {
    font-size: 14px;
    color: var(--GREY1);
}
</style>