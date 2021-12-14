<template>
    <div class="modal fade" id="postEditModal" tabindex="-1" aria-labelledby="postEditModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="postEditModalLabel">Edycja posta</h4>
                    <button type="button" class="btn-close" id="modal-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">

                        <div class="row">
                            <div class="col fw-bolder">
                                Treść posta:
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="grow-wrap">
                                <textarea
                                    class="textarea js-autoresize w-100"
                                    v-model="editedPost.postContent"
                                    placeholder="Podziel się swoimi myślami..."
                                    rows="3"
                                    :class="{ 'has-error': submittingUpdatePost && invalidPostContent}"
                                    @focus="clearStatusPost"
                                    @keypress="clearStatusPost"
                                >
                                </textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row" v-if="!this.editedPostSource.sharing">
                            <div class="col fw-bolder">
                                Zdjęcie:
                            </div>
                        </div>
                        <div class="row" v-if="isWithPhoto && !this.editedPostSource.sharing">
                            <div class="col">
                                <img :src="postPicSrc" alt="Post picture"  id="post-picture" class="w-100">
                            </div>
                            <div class="col">
                                <button class="btn-white" @click="deleteFile">
                                    Usuń zdjęcie
                                </button>
                            </div>
                        </div>
                        <div class="row" v-else-if="!isWithPhoto && !this.editedPostSource.sharing">
                            <input
                                class="form-control"
                                type="file"
                                ref="newPostPicture"
                                id="input-picture"
                                accept="image/png, image/jpeg, image/png"
                            >
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorUpdatePost">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę uzupełnić treść posta!
                                </p>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <button class="btn-panel-social-outline ms-auto" @click="updatePost">
                                Zapisz zmiany
                            </button>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: "PostEdit",
    data() {
        return {
            postPicSrc: "",
            editedPost: Object,
            newPhoto: false,

            submittingUpdatePost: false,
            errorUpdatePost: false,
            successUpdatePost: false
        }
    },
    props: {
        editedPostSource: Object,
    },
    methods: {
        downloadPostPicture () {
            if (this.editedPostSource.postImgPath) {
                const url = `${this.apiURL}post/export/${this.editedPostSource.postId}`
                const token = this.$store.getters.getToken;
                this.$func_global.downloadPhoto(url, token).then(result => this.postPicSrc = result)
            }
        },
        updatePost() {
            this.submittingUpdatePost = true
            this.clearStatusPost()

            if (!this.isWithPhoto && !this.editedPostSource.sharing && this.$refs.newPostPicture.files.length > 0) {
                this.editedPost.postImgPath = this.$refs.newPostPicture.files[0].name
                this.newPhoto = true
            }

            if (this.invalidPostContent) {
                this.errorUpdatePost = true
                return
            }

            const url = `${this.apiURL}post/${this.editedPostSource.postId}`
            const token = this.$store.getters.getToken;
            const data = this.editedPost
            this.axios.patch(url, data, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response)
                if (this.newPhoto) {
                    this.$func_global.importData(this.$refs.newPostPicture, this.$store.getters.getToken, 'postPicture', this.editedPostSource.postId).then((response) => {
                        console.log(response)
                        document.getElementById('modal-close').click();
                        this.$parent.$parent.getEditedPost(this.editedPostSource.postId)
                    })

                } else {
                    document.getElementById('modal-close').click();
                    this.$parent.$parent.getEditedPost(this.editedPostSource.postId)
                }


            }).catch(error => {
                console.log(error.response)
            });
        },
        clearStatusPost() {
            this.submittingUpdatePost = false
            this.errorUpdatePost = false
            this.newPhoto = false
        },
        deleteFile() {
            this.editedPost.postImgPath = null
            this.postPicSrc = ""
        },
    },
    computed: {
        isWithPhoto() {
            return this.postPicSrc !== ""
        },
        invalidPostContent() {
            return this.editedPost.postContent === ""
        },
    },
    watch: {
        editedPostSource: function () {
            this.postPicSrc = ""
            this.editedPost = this.editedPostSource
            this.downloadPostPicture()
        },
    }
}
</script>

<style scoped>
.modal-body {
    color: var(--GREY3);
    text-align: left;
}

.modal-header {
    color: var(--GREY3);
}

textarea {
    padding: 1rem 1rem;
    text-align: left;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;


}

/*.grow-wrap > textarea {*/
/*    resize: none;*/
/*    overflow: hidden;*/
/*}*/
</style>