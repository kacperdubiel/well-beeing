<template>
    <div v-if="commentSource">
        <div class="d-flex flex-row px-4 py-1">
            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="40" width="40">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="40" width="40">
            </div>
            <div class="d-flex flex-column align-self-center ms-1 w-100">
                <div class="row ms-1 mb-1">
                    <div class="text-start d-flex align-items-baseline">
                        <span class="fw-bolder me-1">{{this.commentSource.commenter.firstName}} {{this.commentSource.commenter.lastName}}</span>
                        <span id="time">∙ {{this.$func_global.formatDateDateFromNow(this.commentSource.addedDate)}}</span>
                    </div>
                </div>

                <div class="comment" v-if="!editingComment">
                    {{this.commentSource.comContent}}
                </div>
                <div class="row grow-wrap" v-else>
                    <form>
                        <textarea
                            class="textarea js-autoresize w-100"
                            :id="this.commentSource.commentId"
                            v-model="editedComment.comContent"
                            placeholder="Edytuj komentarz..."
                            :class="{ 'has-error': submittingComment && invalidComment}"
                            @keydown="handleUserKeyPress"
                            @focus="clearStatus"
                            @keypress="clearStatus"
                        >
                        </textarea>
                    </form>
                </div>

                <div class="d-flex flex-row mt-1">
                    <div class="d-flex flex-column">
                        <button class="no-bg-btn text-start ms-2" v-if="isCommentMine && !editingComment" @click="switchEditing">
                            <span class="fw-bolder">Edytuj</span>
                        </button>
                    </div>
                    <div class="d-flex flex-column">
                        <button class="no-bg-btn text-start ms-1" v-if="isCommentMine && !editingComment" @click="deleteComment">
                            <span class="fw-bolder">Usuń</span>
                        </button>
                    </div>
                    <div class="d-flex flex-column">
                        <button class="no-bg-btn text-start ms-1" v-if="isCommentMine && editingComment" @click="updateComment">
                            <span class="fw-bolder">Zapisz zmiany</span>
                        </button>
                    </div>
                    <div class="d-flex flex-column">
                        <button class="no-bg-btn text-start ms-1" v-if="isCommentMine && editingComment" @click="switchEditing">
                            <span class="fw-bolder">Anuluj</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {setResizeListeners} from "@/helpers/auto-resize";
export default {
    name: "Comment",
    props: {
        commentSource: Object
    },
    data() {
        return {
            profilePictureSrc: "",
            editingComment: false,
            editedComment: Object,
            submittingComment: false,
            errorComment: false
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
        },
        switchEditing() {
            this.editingComment = !this.editingComment
            if (this.editingComment)
                setResizeListeners(this.$el, ".js-autoresize")
        },
        updateComment() {
            this.submittingComment = true
            this.clearStatus()

            if (this.invalidComment) {
                this.errorComment = true
                return
            }

            const url = `${this.apiURL}comment/${this.commentSource.commentId}`
            const token = this.$store.getters.getToken;
            this.axios.put(url, this.editedComment, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.submittingComment = false
                this.$parent.$parent.getEditedComment(this.commentSource.commentId)
                this.switchEditing()
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        handleUserKeyPress(e) {
            if (e.key === "Enter" && !e.shiftKey) {
                e.preventDefault();
                this.updateComment()
            }
        },
        clearStatus() {
            this.errorComment = false
        },
    },
    computed: {
        isCommentMine() {
            return this.commentSource.commenter.id === this.$store.getters.getProfileId
        },
        invalidComment() {
            return this.editedComment.comContent === ""
        },
    },
    mounted() {
        this.downloadProfilePicture()
        this.editedComment = this.commentSource
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
    width: fit-content;
    width: -moz-fit-content;
}

#time {
    font-size: 14px;
    color: var(--GREY1);
}

.no-bg-btn {
    font-size: 14px;
}

textarea {
    background-color: var(--GREY2);
    border: none;
    border-radius: 30px;
    padding: 1rem 1rem;
    text-align: left;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    color: white;

}

.grow-wrap > textarea {
    resize: none;
    overflow: hidden;
}

textarea::placeholder {
    color: white;
}
</style>