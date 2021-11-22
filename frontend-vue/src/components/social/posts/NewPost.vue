<template>
    <div class="section-2-bg">
        <div class="d-flex flex-row px-4 py-3">
            <div class="d-flex flex-column text-start">
                <img v-if="profilePictureSrc" :src="profilePictureSrc" alt="Profile picture"  class="profile-picture" height="60" width="60">
                <img v-else src="@/assets/no-photo.png" alt="Profile picture"  class="profile-picture" height="60" width="60">
            </div>
            <div class="d-flex flex-column align-self-center w-100 ms-3">
                <div class="grow-wrap">
                    <textarea
                        class="textarea js-autoresize w-100"
                        v-model="post.postContent"
                        placeholder="Podziel się swoimi myślami..."
                        :class="{ 'has-error': submittingPost && invalidPostContent}"
                        @focus="clearStatusPost"
                        @keypress="clearStatusPost"
                    >
                    </textarea>
                </div>
            </div>
        </div>
        <div class="row text-start px-4 pb-3" v-if="this.post.postContent !== '' || errorPost">
            <div class="col-12 col-md-7">
                <label for="post-picture" class="form-label">Załącz zdjęcie</label>
                <input
                    class="form-control"
                    type="file"
                    ref="postPicture"
                    id="post-picture"
                    accept="image/png, image/jpeg, image/png"
                >
            </div>
            <div class="col-12 col-md-5 align-self-end text-end">
                <button class="btn-panel-social-outline" @click="addPost">
                    Opublikuj
                </button>
            </div>
        </div>
        <div class="row text-start mb-3 px-2" v-if="errorPost">
            <div class="col">
                <p class="has-error m-0">
                    Proszę uzupełnić treść posta!
                </p>
            </div>
        </div>
    </div>
</template>

<script>
import {setResizeListeners} from "@/helpers/auto-resize";
export default {
    name: "NewPost",
    data() {
        return {
            post: {
                postContent: "",
                postImgPath: null

            },
            profilePictureSrc: "",
            submittingPost: false,
            successPost: false,
            errorPost: false
        }
    },
    methods: {
        downloadProfilePicture () {
            const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
            const token = this.$store.getters.getToken;
            this.$func_global.downloadPhoto(url, token).then(result => this.profilePictureSrc = result)
        },
        addPost() {
            this.submittingPost = true
            this.clearStatusPost()

            if (this.$refs.postPicture.files.length > 0) {
                this.post.postImgPath = this.$refs.postPicture.files[0].name
            }

            if (this.invalidPostContent) {
                this.errorPost = true
                return
            }

            const url = `${this.apiURL}post`
            const token = this.$store.getters.getToken
            this.axios.post(url, this.post, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                let postId = response.data['postId']
                if (this.post.postImgPath !== null)
                    this.$func_global.importData(this.$refs.postPicture, this.$store.getters.getToken, 'postPicture', postId)
                this.clearInputs()
                this.successPost = true
                this.submittingPost = false
                console.log('DATATATATTATA',response.data)
                this.$emit('refresh:posts', 0, false, 0)
            }).catch(error => {
                console.log(error.response)
            });
        },
        clearStatusPost() {
            this.successPost = false
            this.errorPost = false
        },
        clearInputs() {
            this.post.postContent = ""
            this.post.postImgPath = null
            document.getElementById("post-picture").value = ""

        }
    },
    mounted() {
        this.downloadProfilePicture()
        setResizeListeners(this.$el, ".js-autoresize")
    },
    computed: {
        invalidPostContent() {
            return this.post.postContent === ""
        },
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