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
                <div class="d-flex flex-row mt-1">
                    <div class="d-flex flex-column">
                        <button class="no-bg-btn text-start ms-2" v-if="isCommentMine">
                            <span class="fw-bolder">Edytuj</span>
                        </button>
                    </div>
                    <div class="d-flex flex-column">
                        <button class="no-bg-btn text-start ms-1" v-if="isCommentMine" @click="deleteComment">
                            <span class="fw-bolder">Usuń</span>
                        </button>
                    </div>
                </div>
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
        deleteComment() {
            const url = `${this.apiURL}comment/${this.commentSource.commentId}/delete`
            const token = this.$store.getters.getToken;
            this.axios.patch(url, null, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.$parent.$parent.getCommentsAfterDelete()
            }).catch(error => {
                console.log(error.response.status)
            });
        }
    },
    computed: {
        isCommentMine() {
            return this.commentSource.commenter.id === this.$store.getters.getProfileId
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

.no-bg-btn {
    font-size: 14px;
}
</style>