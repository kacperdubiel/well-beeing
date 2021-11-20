<template>
    <div>
        <div class="d-flex flex-row px-4 py-3">
            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="40" width="40">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="40" width="40">
            </div>
            <div class="d-flex flex-column align-self-center w-100 ms-3">
                <div class="grow-wrap">
                    <form>
                        <textarea
                            class="textarea js-autoresize w-100"
                            :id="this.postId"
                            v-model="comment.comContent"
                            placeholder="Dodaj komentarz..."
                            :class="{ 'has-error': submittingComment && invalidComment}"
                            @keydown="handleUserKeyPress"
                            @focus="clearStatus"
                            @keypress="clearStatus"
                        >
                    </textarea>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {setResizeListeners} from "@/helpers/auto-resize";
export default {
    name: "NewComment",
    data () {
        return {
            comment: {
                comContent: ""
            },
            profilePictureSrc: "",
            submittingComment: false,
            successComment: false,
            errorComment: false
        }
    },
    props: {
        postId: Number
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        addComment() {
            this.successComment = true
            this.clearStatus()

            if (this.invalidComment) {
                this.errorComment = true
                return
            }

            const url = `${this.apiURL}post/${this.postId}/comment`
            const token = this.$store.getters.getToken
            this.axios.post(url, this.comment, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response)
                this.comment.comContent =""
                this.successComment = true
                this.submittingComment = false
                // this.$emit('refresh:posts', 0, false, 0)
            }).catch(error => {
                console.log(error.response)
            });
        },
        clearStatus() {
            this.successComment = false
            this.errorComment = false
        },
        submitOnEnter(event){
            if(event.which === 13 && !event.shiftKey){
                event.target.form.dispatchEvent(new Event("submit", {cancelable: true}));
                event.preventDefault();
            }
        },
        handleUserKeyPress(e) {
            if (e.key === "Enter" && !e.shiftKey) {
                e.preventDefault();
                this.addComment()
            }
        }
    },
    computed: {
        invalidComment() {
            return this.comment.comContent === ""
        },
    },
    mounted() {
        this.downloadProfilePicture()
        document.getElementById(String(this.postId)).style.height = '60px'
        setResizeListeners(this.$el, ".js-autoresize")
    }
}
</script>

<style scoped>
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